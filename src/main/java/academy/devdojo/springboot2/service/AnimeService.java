package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.components.AnimeComponent;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.domain.dto.AnimeDTO;
import academy.devdojo.springboot2.domain.parser.AnimeParse;
import academy.devdojo.springboot2.exceptions.BadRequestException;
import academy.devdojo.springboot2.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    private final AnimeComponent animeComponent;

    public Page<Anime> findAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<Anime> findAll(){
        return animeRepository.findAll();
    }

    public AnimeDTO findById(Long id){

        Anime anime = animeComponent.findById(id);
        return AnimeParse.parseAnimeToAnimeDTO(anime);
    }

    public AnimeDTO save(AnimeDTO dto) {
        Anime anime = animeComponent.create(dto);
        anime =  animeComponent.save(anime);
        return AnimeParse.parseAnimeToAnimeDTO(anime);
    }

    public AnimeDTO update(AnimeDTO dto){

        Anime anime = animeComponent.findById(dto.getId());
        animeComponent.updateData(anime,dto);
        anime = animeComponent.save(anime);
        return AnimeParse.parseAnimeToAnimeDTO(anime);
    }


    public void delete(Long id) {
        animeComponent.delete(animeComponent.findById(id));
    }
}
