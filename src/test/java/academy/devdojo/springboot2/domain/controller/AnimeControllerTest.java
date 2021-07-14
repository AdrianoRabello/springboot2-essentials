package academy.devdojo.springboot2.domain.controller;

import academy.devdojo.springboot2.components.AnimeComponent;
import academy.devdojo.springboot2.controller.AnimeController;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.domain.datapol.AnimeDataPol;
import academy.devdojo.springboot2.domain.dto.AnimeDTO;
import academy.devdojo.springboot2.service.AnimeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

    @InjectMocks
    private  AnimeController animeController;

    @Mock
    private AnimeService animeService;

    @Mock
    private AnimeComponent animeComponent;

    @BeforeEach
    void beforeEach(){

        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeDataPol.getAnimeSaved()));

        Mockito.when(animeService.findAll(ArgumentMatchers.any())).thenReturn(animePage);

        Mockito.when(animeService.findAll()).thenReturn(List.of(AnimeDataPol.getAnimeSaved(),AnimeDataPol.getAnimeSaved()));

        Mockito.when(animeService.findById(ArgumentMatchers.any())).thenReturn(AnimeDataPol.crateAnimeDTO());

        Mockito.when(animeComponent.create(ArgumentMatchers.any())).thenReturn(AnimeDataPol.getAnimeSaved());

        Mockito.when(animeComponent.save(ArgumentMatchers.any(Anime.class))).thenReturn(AnimeDataPol.getAnimeSaved());

        Mockito.when(animeService.save(ArgumentMatchers.any(AnimeDTO.class))).thenReturn(AnimeDataPol.crateAnimeDTO());

    }

    @Test
    @DisplayName("Should not return empt page")
    void shuldNotRetournEmptyPage(){

        Anime animeSaved = AnimeDataPol.getAnimeSaved();

        Page<Anime> animePage = animeController.page(null).getBody();

        Assertions.assertThat(!animePage.getContent().isEmpty());
        Assertions.assertThat(animePage.getContent().get(0).getName().equals(animeSaved.getName()));
        Assertions.assertThat(animePage.getContent().get(0).getName()).isEqualTo(animeSaved.getName());
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
    }

    @Test
    @DisplayName("shuld display list of Anime")
    void shouldHasListOfAnime(){

        Assertions.assertThat(animeController.list().getBody()).isNotEmpty().hasSize(2);
    }

    @Test
    @DisplayName("Shoud return Anime by id")
    void shouldFindById(){
        Anime animeSaved = AnimeDataPol.getAnimeSaved();

        Assertions.assertThat(animeController.findById(1L).getBody().getId()).isNotNull().isEqualTo(animeSaved.getId());
    }

    @Test
    @DisplayName("should parse dto to anime and save anime and parse to dto")
    void shouldParseDtoToAnimeAndSave(){

        Assertions.assertThat(animeController.save(AnimeDataPol.crateAnimeDTO()).getBody()).isNotNull();

        Assertions.assertThat(animeController.save(AnimeDataPol.crateAnimeDTO()).getBody().getName()).isEqualTo(AnimeDataPol.getAnimeSaved().getName());

        org.junit.jupiter.api.Assertions.assertTrue(animeController.save(AnimeDataPol.crateAnimeDTO()).getBody().getCreated() != null);
    }


}
