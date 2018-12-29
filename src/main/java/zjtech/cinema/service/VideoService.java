package zjtech.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import zjtech.cinema.common.VideoTypeEnum;
import zjtech.cinema.dto.VideoDto;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class VideoService {
  @Autowired
  private MongoOperations mongoOperations;

  @Autowired
  private VideoRepository videoRepository;

  public void ensureIndex() {
    mongoOperations.indexOps(VideoDto.class).ensureIndex(new Index().named("vodType"));
  }

  public List<VideoDto> getVideoByType(VideoTypeEnum types) {
//    Criteria criteria = new Criteria();
//    where("videoType").in()
//       Query query = new Query();
//    query.addCriteria()
//
//    mongoOperations.query(VideoDto.class).matching(query);
//
//
//    videoRepository.
    return  new ArrayList<>();
  }
}
