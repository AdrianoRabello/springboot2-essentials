package academy.devdojo.springboot2.parser;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDTO;

public class AnimeParse {

    public static Anime parseAnimeDTOToAnime(AnimeDTO dto){

        return new Anime.Builder()
                .id(dto.getId())
                .name(dto.getName())
                .created(dto.getCreated())
                .build();
    }

    public static AnimeDTO parseAnimeToAnimeDTO(Anime anime){

        return AnimeDTO.builder()
                .id(anime.getId())
                .name(anime.getName())
                .created(anime.getCreated())
                .build();
    }
}
