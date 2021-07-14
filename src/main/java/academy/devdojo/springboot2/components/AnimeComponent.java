package academy.devdojo.springboot2.components;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.domain.dto.AnimeDTO;
import academy.devdojo.springboot2.exceptions.BadRequestException;
import academy.devdojo.springboot2.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AnimeComponent {


    private final AnimeRepository animeRepository;


    public Anime create(AnimeDTO dto){
        return Anime.builder().name(dto.getName()).id(dto.getId()).created(LocalDateTime.now()).build();
    }

    public void updateData(Anime anime, AnimeDTO dto){
        anime.setName(dto.getName());
        anime.setUpdated(LocalDateTime.now());
    }


    public Anime save(Anime anime){

        return animeRepository.save(anime);
    }

    public void delete(Anime anime){
        animeRepository.delete(anime);
    }

    public Anime findById(Long id){
        return animeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Resource not found"));
    }

}
