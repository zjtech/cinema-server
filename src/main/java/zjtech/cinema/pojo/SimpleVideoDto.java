package zjtech.cinema.pojo;

import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleVideoDto {

  private String id;
  private String name;
  private String actors;
  private String imageUrl;

  public static Stream<String> includeFields() {
    return Stream.of("id", "name", "actors", "imageUrl");
  }

}
