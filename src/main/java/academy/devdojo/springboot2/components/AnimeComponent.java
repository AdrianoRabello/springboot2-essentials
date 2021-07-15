package academy.devdojo.springboot2.components;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDTO;
import academy.devdojo.springboot2.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AnimeComponent {


    private final AnimeRepository animeRepository;


    public Anime save(Anime anime) {

        return animeRepository.save(anime);
    }

    public Anime update(Anime anime){

        anime.setUpdated(LocalDateTime.now());
        return animeRepository.save(anime);
    }

    public Anime create(AnimeDTO dto) {
        return Anime.builder().name(dto.getName()).created(LocalDateTime.now()).build();
    }

    public Anime updateData(Anime anime, AnimeDTO dto) {
        anime.setName(dto.getName());
        return anime;
    }





    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    public Page<Anime> findAll(Pageable pageable) {

        return animeRepository.findAll(pageable);
    }

    public void delete(Anime anime) {
        animeRepository.delete(anime);
    }

    public Anime findById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resource not found"));
    }

}
