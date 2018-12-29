package zjtech.cinema.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VideoVendorDto implements Serializable {

  private String name;
  private String displayName;
  private List<PlayInfo> playList = new ArrayList<>();
}
