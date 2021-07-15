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
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
        Mockito.when(animeComponent.update(ArgumentMatchers.any())).thenReturn(AnimeDataPol.getAnimeUpdated());
        Mockito.when(animeComponent.create(ArgumentMatchers.any())).thenReturn(AnimeDataPol.createAnimeToSave());
        Mockito.when(animeComponent.findAll(ArgumentMatchers.any())).thenReturn(animeDTOPage);
        Mockito.when(animeComponent.findAll()).thenReturn(List.of(AnimeDataPol.getAnimeSaved()));
        Mockito.when(animeComponent.findById(ArgumentMatchers.any(Long.TYPE))).thenReturn(AnimeDataPol.getAnimeSaved());
        Mockito.when(animeRepository.findById(ArgumentMatchers.any(Long.TYPE))).thenReturn(Optional.empty());
        Mockito.when(animeComponent.updateData(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(AnimeDataPol.getAnimeUpdated());

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

    @Test
    void should_return_anime_by_id(){
        Assertions.assertThat(animeService.findById(1L).getClass()).isEqualTo(AnimeDTO.class);
    }

    @Test
    void should_return_anime_by_id_with_exception(){

//        Mockito.doNothing(animeComponent.findById(1L));
//
//        Mockito.when(animeComponent.findById(1L)).thenReturn(animeRepository.findById(50L).get());
//
//        Assertions.assertThatExceptionOfType(ResponseStatusException.class).isThrownBy(()->{
//            animeService.findById(50L);
//        });



    }

    @Test
    void should_return_anime_updated(){
        Anime animeUpdated = AnimeDataPol.getAnimeUpdated();
        Assertions.assertThat(animeService.update(AnimeDataPol.getAnimeDTOUpdated()).getName()).isEqualTo(animeUpdated.getName());
        Assertions.assertThat(animeService.update(AnimeDataPol.getAnimeDTOUpdated()).getClass()).isEqualTo(AnimeDTO.class);
    }

    @Test
    void should_return_animeDTO_list(){
        Assertions.assertThat(animeService.findAll()).isNotEmpty().isNotNull();
        Assertions.assertThat(animeService.findAll().get(0).getClass()).isEqualTo(AnimeDTO.class);
    }




}
