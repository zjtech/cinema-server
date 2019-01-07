package zjtech.cinema.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import zjtech.cinema.dto.VideoDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideMongoTest {

  @Autowired
  private MongoOperations mongoTemplate;

  @Test
  public void testMoviePage() {
    Pageable pageable = PageRequest.of(0, 10);

    Query query = new Query(where("videoCatalog").is("1"));
    query.with(Sort.by(new Order(Direction.DESC, "updateTime")))
        .with(pageable);
    List<VideoDto> list = mongoTemplate.find(query, VideoDto.class, VideoDto.COLLECTION_NAME);
    System.out.println("list=" + list.size());

    pageable = PageRequest.of(1, 10);
    query.with(pageable);
    List<VideoDto> list2 = mongoTemplate.find(query, VideoDto.class, VideoDto.COLLECTION_NAME);
    System.out.println("list=" + list.size());
  }

}
