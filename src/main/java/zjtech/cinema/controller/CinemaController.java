package zjtech.cinema.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjtech.cinema.common.VideoTypes;
import zjtech.cinema.dto.VideoDto;
import zjtech.cinema.pojo.PageDto;
import zjtech.cinema.pojo.SimpleVideoDto;
import zjtech.cinema.service.CinemaService;
import zjtech.cinema.service.VideoCatalogMapper;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

  @Value("${video.type.movie}")
  private String movieType;

  @Value("${video.type.teleplay}")
  private String teleplayType;

  @Value("${video.type.vshow}")
  private String vshowType;

  @Value("${video.type.cartoon}")
  private String cartoonType;

  @Autowired
  private CinemaService cinemaService;

  @Autowired
  private VideoCatalogMapper catalogMapper;

  @GetMapping("home")
  public Mono<Map<String, List<VideoDto>>> getHomeVideoList() {
    List<VideoDto> movieList = cinemaService.getLatestVideos(6, movieType);
    List<VideoDto> tvPlayList = cinemaService.getLatestVideos(6, teleplayType);
    List<VideoDto> varietyShowList = cinemaService.getLatestVideos(6, vshowType);
    List<VideoDto> cartoonList = cinemaService.getLatestVideos(6, cartoonType);

    Map<String, List<VideoDto>> map = new HashMap<>();
    map.put(VideoTypes.movie.name(), movieList);
    map.put(VideoTypes.teleplay.name(), tvPlayList);
    map.put(VideoTypes.varietyShow.name(), varietyShowList);
    map.put(VideoTypes.cartoon.name(), cartoonList);
    return Mono.just(map);
  }

  @GetMapping("video")
  public Mono<PageDto<SimpleVideoDto>> getHomeVideoList(@RequestParam("type") String type,
      @RequestParam("page") int page) {
    VideoTypes videoType = VideoTypes.valueOf(type);
    if (videoType == null || page < 0 || page > Integer.MAX_VALUE) {
      throw new IllegalArgumentException();
    }
    String videoCatalog = catalogMapper.getVideoCatalog(videoType);

    PageDto<SimpleVideoDto> pageDto = cinemaService.getSimpleVideosPage(page, videoCatalog);
    return Mono.just(pageDto);
  }

  @GetMapping("video/{id}")
  public Mono<VideoDto> retrieveVideo(@PathVariable("id") String id) {
    if (StringUtils.isEmpty(id)) {
      throw new IllegalArgumentException("Invalid id for retrieving a specific video");
    }
    VideoDto dto = cinemaService.findById(id);
    return Mono.just(dto);
  }

  @GetMapping("video/favorite")
  public Flux<VideoDto> getFavoriteList(
      @RequestParam("videoType") String videoType) {
    if (StringUtils.isEmpty(videoType)) {
      throw new IllegalArgumentException(
          "Invalid videoType for retrieving a list of favorite videos");
    }
    List<VideoDto> movieList = new ArrayList();
    return Flux.fromIterable(movieList);
  }
}
