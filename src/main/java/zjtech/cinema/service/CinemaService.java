package zjtech.cinema.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zjtech.cinema.dto.VideoDto;
import zjtech.cinema.pojo.PageDto;
import zjtech.cinema.pojo.SimpleVideoDto;

@Service
public class CinemaService {

  @Autowired
  private MongoOperations mongoOperations;

  @Autowired
  private VideoProviderMapper providerMapper;

  @Value("${video.page-size}")
  private int pageSize = 1;

  private static SimpleVideoDto apply(VideoDto videoDto) {
    SimpleVideoDto simpleVideoDto = new SimpleVideoDto();
    BeanUtils.copyProperties(videoDto, simpleVideoDto);
    return simpleVideoDto;
  }


  /**
   * Get videos for home page
   */
  public List<VideoDto> getLatestVideos(int size, String videoCatalog) {
    return findByVideoCatalog(0, size, videoCatalog);
  }

  /**
   * Get simple videos page
   */
  public PageDto<SimpleVideoDto> getSimpleVideosPage(int page, String videoCatalog) {
    List<SimpleVideoDto> list = findSimpleByVideoCatalog(page, pageSize, videoCatalog);

    Query query = new Query(where("videoCatalog").is(videoCatalog));
    int totalCount = (int) mongoOperations.count(query, VideoDto.class, VideoDto.COLLECTION_NAME);
    int totalPages = (totalCount + pageSize - 1) / pageSize;

    PageDto pageDto = new PageDto();
    pageDto.setCurrentPage(page);
    pageDto.setTotalCount(totalCount);
    pageDto.setTotalPages(totalPages);
    pageDto.setVideoList(list);
    return pageDto;
  }

  /**
   * Find a document by id
   *
   * @param id ID
   */
  public VideoDto findById(String id) {
    VideoDto videoDto = mongoOperations.findById(id, VideoDto.class, VideoDto.COLLECTION_NAME);
    return updateProviderName(videoDto);
  }

  private List<VideoDto> updateProvidersName(List<VideoDto> videoDtoList) {
    videoDtoList.forEach(this::updateProviderName);
    return videoDtoList;
  }

  private VideoDto updateProviderName(VideoDto videoDto) {
    if (videoDto == null) {
      return null;
    }
    videoDto.getVendors().forEach(vendor -> {
      if (StringUtils.isEmpty(vendor.getDisplayName())) {
        String realVendorName = providerMapper.getProviderName(vendor.getName());
        vendor.setDisplayName(realVendorName);
      }
    });
    return videoDto;
  }

  private List<VideoDto> findByVideoCatalog(int page, int size, String videoCatalog) {
    Pageable pageable = PageRequest.of(page, size);

    Query query = new Query(where("videoCatalog").is(videoCatalog));
    query.with(Sort.by(new Order(Direction.DESC, "updateTime")))
        .with(pageable);
    List<VideoDto> videoDtoList = mongoOperations
        .find(query, VideoDto.class, VideoDto.COLLECTION_NAME);

    //update provider name
    return updateProvidersName(videoDtoList);
  }

  private List<SimpleVideoDto> findSimpleByVideoCatalog(int page, int size, String videoCatalog) {
    Pageable pageable = PageRequest.of(page, size);

    //only query partial fields
    Query query = new Query(where("videoCatalog").is(videoCatalog));
    SimpleVideoDto.includeFields().forEach(field -> query.fields().include(field));

    query.with(Sort.by(new Order(Direction.DESC, "updateTime")))
        .with(pageable);
    List<VideoDto> videoDtoList = mongoOperations
        .find(query, VideoDto.class, VideoDto.COLLECTION_NAME);

    //convert to simple video list
    return videoDtoList.stream().map(CinemaService::apply).collect(Collectors.toList());
  }

}
