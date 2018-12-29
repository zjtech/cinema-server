package zjtech.cinema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zjtech.cinema.service.VideoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class VideoServiceTest {

  @Autowired
  private VideoService videoService;

  @Test
  public void testEnsureIndexes() {
//    videoService.ensureIndex();
  }

}
