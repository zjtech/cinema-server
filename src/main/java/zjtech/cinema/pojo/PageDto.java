package zjtech.cinema.pojo;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto<T> {

  private int currentPage;
  private int totalPages;
  private int totalCount;

  private List<T> videoList = new ArrayList<>();
}
