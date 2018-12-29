package zjtech.cinema.service;

import org.springframework.data.repository.CrudRepository;
import zjtech.cinema.dto.VideoDto;

public interface VideoRepository extends CrudRepository<VideoDto, String> {
}
