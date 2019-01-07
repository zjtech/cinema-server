package zjtech.cinema.service;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class VideoProviderMapper {

  private Map<String, String> providerMap = new HashMap<>();

  @PostConstruct
  public void postConstruct() {
    providerMap.put("mgtv", "芒果TV");
    providerMap.put("pptv", "PPTV");
    providerMap.put("iqiyi", "爱奇艺");
    providerMap.put("qiyi", "爱奇艺");
    providerMap.put("qq", "腾讯视频");
    providerMap.put("youku", "优酷");
  }

  public String getProviderName(String name) {
    String value = providerMap.get(name);
    if (StringUtils.isEmpty(value)) {
      value = name;
    }
    return value;
  }

}
