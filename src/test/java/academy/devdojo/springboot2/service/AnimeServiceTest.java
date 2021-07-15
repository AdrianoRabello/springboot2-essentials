package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.components.AnimeComponent;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.datapol.AnimeDataPol;
import academy.devdojo.springboot2.dto.AnimeDTO;
import academy.devdojo.springboot2.repository.AnimeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTest {

    @InjectMocks
   private  AnimeService animeService;

    @Mock
    private AnimeRepository animeRepository;

    @Mock
    private AnimeComponent animeComponent;

    @BeforeEach
    void initilizeMocks(){

        PageImpl<Anime> animeDTOPage = new PageImpl<>(List.of(AnimeDataPol.getAnimeSaved()));

        Mockito.when(animeComponent.save(ArgumentMatchers.any())).thenReturn(AnimeDataPol.getAnimeSaved());
        Mockito.when(animeComponent.create(ArgumentMatchers.any())).thenReturn(AnimeDataPol.createAnimeToSave());
        Mockito.when(animeComponent.findAll(ArgumentMatchers.any())).thenReturn(animeDTOPage);

    }

    @Test
    void shoul_save_anime_object_return_animeDTO(){
       Assertions.assertThat(animeService.save(AnimeDataPol.crateAnimeDTO())).isNotNull();

       Assertions.assertThat(animeService.save(AnimeDataPol.crateAnimeDTO()).getClass()).isEqualTo(AnimeDTO.class);

       Assertions.assertThat(animeService.save(AnimeDataPol.crateAnimeDTO()).getCreated()).isNotNull();

    }

    @Test
    @DisplayName("Shound return a pageable of dto class")
    void shoud_return_anime_page_when_success(){

        Assertions.assertThat(animeService.findAll(null).getContent())
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(animeService.findAll(null).getContent().get(0).getClass()).isEqualTo(AnimeDTO.class);

    }

}
