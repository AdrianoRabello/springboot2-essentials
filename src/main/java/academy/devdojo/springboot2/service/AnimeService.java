package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.components.AnimeComponent;
import academy.devdojo.springboot2.dto.AnimeDTO;
import academy.devdojo.springboot2.parser.AnimeParse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeService {


    private final AnimeComponent animeComponent;

    public Page<AnimeDTO> findAll(Pageable pageable) {
        Page<Anime> all = animeComponent.findAll(pageable);
        PageImpl<AnimeDTO> animeDTOPage = new PageImpl<>(all.getContent().stream().map(anime -> AnimeParse.parseAnimeToAnimeDTO(anime)).collect(Collectors.toList()));
        return animeDTOPage;
    }

    public List<AnimeDTO> findAll() {
        List<Anime> animeDTOList = animeComponent.findAll();

        return animeDTOList.stream().map(dto -> AnimeParse.parseAnimeToAnimeDTO(dto)).collect(Collectors.toList());
    }

    public AnimeDTO findById(Long id) {

        Anime anime = animeComponent.findById(id);
        return AnimeParse.parseAnimeToAnimeDTO(anime);
    }

    public AnimeDTO save(AnimeDTO dto) {
        Anime anime = animeComponent.create(dto);
        anime = animeComponent.save(anime);
        return AnimeParse.parseAnimeToAnimeDTO(anime);
    }

    public AnimeDTO update(AnimeDTO dto) {

        Anime anime = animeComponent.findById(dto.getId());
        anime = animeComponent.updateData(anime, dto);
        anime = animeComponent.update(anime);
        return AnimeParse.parseAnimeToAnimeDTO(anime);
    }


    public void delete(Long id) {
        animeComponent.delete(animeComponent.findById(id));
    }
}
