package academy.devdojo.springboot2.datapol;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDTO;

import java.time.LocalDateTime;

public class AnimeDataPol {

    public static Anime createAnimeToSave(){
        return new Anime.Builder().name("Naruto").created(LocalDateTime.now()).build();
    }

    public static Anime getAnimeSaved(){
        return  new Anime.Builder().id(1L).name("Naruto").created(LocalDateTime.now()).build();
    }

    public static Anime getAnimeUpdated(){
        return  new Anime.Builder().id(1L).name("Naruto 2").updated(LocalDateTime.now()).build();
    }

    public static AnimeDTO crateAnimeDTO(){
        return AnimeDTO.builder()
                .name("Naruto")
                .build();
    }

    public static AnimeDTO getAnimeDTOSaved(){
        return AnimeDTO.builder()
                .id(1L)
                .created(LocalDateTime.now())
                .name("Naruto")
                .build();
    }

    public static AnimeDTO getAnimeDTOUpdated(){
        return AnimeDTO.builder()
                .id(1L)
                .name("Naruto 2")
                .updated(LocalDateTime.now())
                .build();
    }
}
