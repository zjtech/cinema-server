package zjtech.cinema.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "video.type")
@Component
@Getter
@Setter
public class VideoTypeProp {
  private String movie;
  private String tv;
  private String show;
  private String cartoon;
}
