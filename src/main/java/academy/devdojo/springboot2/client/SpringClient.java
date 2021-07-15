package academy.devdojo.springboot2.client;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> forEntity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);

        log.info(forEntity.toString());
        log.info(object.toString());


        // to get a array with resttemplate
        ResponseEntity<Anime[]> forEntity1 = new RestTemplate().getForEntity("http://localhost:8080/animes", Anime[].class);

        ResponseEntity<List<Anime>> animeList = new RestTemplate()
                .exchange("http://localhost:8080/animes",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });

        log.info(animeList.toString());

        AnimeDTO samuraichampo = AnimeDTO.builder().name("Samurai champo").build();
        Anime samuraichampoSavad = new RestTemplate().postForObject("http://localhost:8080/animes", samuraichampo, Anime.class);
        log.info(samuraichampoSavad.toString());


        AnimeDTO cavaleiros = AnimeDTO.builder().name("Cavaleiros dos odiacos").build();

        ResponseEntity<AnimeDTO> postExchange = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.POST, new HttpEntity<>(cavaleiros, createHeader()), AnimeDTO.class);

        log.info(postExchange.getBody().toString());


    }

    // to create header with token
    private static HttpHeaders createHeader(){

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return httpHeaders;

    }
}
