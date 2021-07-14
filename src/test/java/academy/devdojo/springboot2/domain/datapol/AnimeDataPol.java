package academy.devdojo.springboot2.domain.datapol;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.domain.dto.AnimeDTO;

import java.time.LocalDateTime;

public class AnimeDataPol {

    public static Anime createAnimeToSave(){
        return Anime.builder().name("Naruto").build();
    }

    public static Anime getAnimeSaved(){
        return Anime.builder().id(1L).name("Naruto").build();
    }

    public static Anime getAnimeUpdated(){
        return Anime.builder().id(1L).name("Naruto 2").build();
    }

    public static AnimeDTO crateAnimeDTO(){
        return AnimeDTO.builder()
                .id(1L).name("Naruto")
                .created(LocalDateTime.now())
                .build();
    }
}
