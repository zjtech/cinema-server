package zjtech.cinema.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjtech.cinema.common.VideoTypes;
import zjtech.cinema.document.PlayInfo;
import zjtech.cinema.document.VideoDto;
import zjtech.cinema.document.VideoVendorDto;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

  @GetMapping("video")
  public Mono<Map<String, List<VideoDto>>> getHomeVideoList() {
    List<VideoDto> movieList = new ArrayList();
    List<VideoDto> tvPlayList = new ArrayList();
    List<VideoDto> varietyShowList = new ArrayList();
    List<VideoDto> cartoonList = new ArrayList();

    //for movie
    VideoDto videoDto = new VideoDto();
    videoDto.setId("11");
    videoDto.setName("movie name1");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("12");
    videoDto.setName("movie name2");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("13");
    videoDto.setName("movie name3");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("14");
    videoDto.setName("movie name4");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    //for tvplay
    videoDto = new VideoDto();
    videoDto.setId("15");
    videoDto.setName("tv name1");
    videoDto.setActors("act1,act2,act3,act4");
    tvPlayList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("16");
    videoDto.setName("tv name2");
    videoDto.setActors("act1,act2,act3,act4");
    tvPlayList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("17");
    videoDto.setName("tv name3");
    videoDto.setActors("act1,act2,act3,act4");
    tvPlayList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("18");
    videoDto.setName("tv name4");
    videoDto.setActors("act1,act2,act3,act4");
    tvPlayList.add(videoDto);

    //for varietyShowList
    videoDto = new VideoDto();
    videoDto.setId("20");
    videoDto.setName("variety name1");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("21");
    videoDto.setName("variety name2");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("22");
    videoDto.setName("variety name3");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("23");
    videoDto.setName("variety name4");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    //for cartoonList
    videoDto = new VideoDto();
    videoDto.setId("30");
    videoDto.setName("cartoon name1");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("31");
    videoDto.setName("cartoon name2");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("33");
    videoDto.setName("cartoon name3");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("34");
    videoDto.setName("cartoon name4");
    videoDto.setActors("act1,act2,act3,act4");
    varietyShowList.add(videoDto);

    Map<String, List<VideoDto>> map = new HashMap<>();
    map.put(VideoTypes.movie.name(), movieList);
    map.put(VideoTypes.teleplay.name(), tvPlayList);
    map.put(VideoTypes.varietyShow.name(), varietyShowList);
    map.put(VideoTypes.cartoon.name(), cartoonList);
    return Mono.just(map);
  }

  @GetMapping("video/{id}")
  public Mono<VideoDto> retrieveVideo(@PathVariable("id") String id) {
    if (StringUtils.isEmpty(id)) {
      throw new IllegalArgumentException("Invalid id for retrieving a specific video");
    }
    VideoDto videoDto = new VideoDto();
    videoDto.setId(id);
    videoDto.setName("video " + id);
    videoDto.setDirector("Jeven Wang dir, Holy Pick");
    videoDto.setActors("Jeason xx,carl,fu,wangzhaoju");
    videoDto.setVideoType("Juqing");
    videoDto.setIntroduction(
        "desc因为家族企业遇到经营困境，厉致诚临危受命，成为一家企业的负责人，"
            + "并且因此邂逅刚出校门、 创业碰壁的都市白领林浅。由于厉致诚全无商业经验，"
            + "所以企业内外部都不看好这位总裁。 林浅是一位非常正直善良的女性,凭借自己的职场经验和理想追求,"
            + "给予厉致诚很大帮助。 厉致诚将用自己的智慧和独特方法运用到企业的实际经营中，竟令企业起死回生， "
            + "并且一步步走向辉煌；而他和林浅两人也在这个过程中相识、相知、相爱。然而这时，"
            + " 国际外资企业亦看到了该公司的丰厚利润和发展前景，提出了高额收购。 怀揣着兴企爱国的梦想，"
            + "厉致诚、林浅等年轻民营企业家，拒绝了收购， 而是致力于产品的质量、创意提高和企业形象的经营。"
            + "最终，二人使企业一步步走向世界，并且收获了爱情。");

    VideoVendorDto vendorDto = new VideoVendorDto();
    vendorDto.setName("qiyi");
    vendorDto.setDisplayName("爱奇艺");

    PlayInfo playInfo = new PlayInfo();
    playInfo.setName("playUrl");
    playInfo.setPlayUrl("http://qiyi/play");

    vendorDto.getPlayList().add(playInfo);
    videoDto.getVendors().add(vendorDto);

    vendorDto = new VideoVendorDto();
    vendorDto.setName("tecent");
    vendorDto.setDisplayName("腾讯");
    videoDto.getVendors().add(vendorDto);

    return Mono.just(videoDto);
  }

  @GetMapping("video/favorite")
  public Flux<VideoDto> getFavoriteList(
      @RequestParam("videoType") String videoType) {
    if (StringUtils.isEmpty(videoType)) {
      throw new IllegalArgumentException(
          "Invalid videoType for retrieving a list of favorite videos");
    }
    List<VideoDto> movieList = new ArrayList();

    //for movie
    VideoDto videoDto = new VideoDto();
    videoDto.setId("11");
    videoDto.setName("movie name1");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("12");
    videoDto.setName("movie name2");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("13");
    videoDto.setName("movie name3");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    videoDto = new VideoDto();
    videoDto.setId("14");
    videoDto.setName("movie name4");
    videoDto.setActors("act1,act2,act3,act4");
    movieList.add(videoDto);

    return Flux.fromIterable(movieList);
  }
}
