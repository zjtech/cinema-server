package zjtech.cinema.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PlayInfo implements Serializable {

  private String name;
  private String playUrl;

}
