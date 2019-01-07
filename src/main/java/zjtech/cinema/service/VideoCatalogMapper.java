package zjtech.cinema.service;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import zjtech.cinema.common.CinemaException;
import zjtech.cinema.common.VideoTypes;

@Component
public class VideoCatalogMapper {

  private Map<VideoTypes, String> catalogMap = new HashMap<>();

  @Value("${video.type.movie}")
  private String movie;

  @Value("${video.type.teleplay}")
  private String teleplay;

  @Value("${video.type.vshow}")
  private String vshow;

  @Value("${video.type.cartoon}")
  private String cartoon;

  @PostConstruct
  public void postConstruct() {
    catalogMap.put(VideoTypes.movie, movie);
    catalogMap.put(VideoTypes.teleplay, teleplay);
    catalogMap.put(VideoTypes.varietyShow, vshow);
    catalogMap.put(VideoTypes.cartoon, cartoon);
  }

  public String getVideoCatalog(VideoTypes type) {
    String value = catalogMap.get(type);
    if (StringUtils.isEmpty(value)) {
      throw new CinemaException("no catalog found for " + type.toString());
    }
    return value;
  }

}
