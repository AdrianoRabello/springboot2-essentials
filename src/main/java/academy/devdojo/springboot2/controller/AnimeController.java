package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDTO;
import academy.devdojo.springboot2.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
@Slf4j
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list(){

        return ResponseEntity.ok().body(animeService.findAll());
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<AnimeDTO>> page(Pageable pageable){

        return ResponseEntity.ok().body(animeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDTO> findById(@PathVariable Long id){

        log.info("get anime by id");
        AnimeDTO anime = animeService.findById(id);
        return ResponseEntity.ok().body(anime);
    }

    @PostMapping
    public ResponseEntity<AnimeDTO> save(@RequestBody @Valid AnimeDTO dto){
        log.info("saving anime");
        AnimeDTO anime = animeService.save(dto);
        return ResponseEntity.ok().body(anime);
    }

    @PutMapping
    public ResponseEntity<AnimeDTO> update(@RequestBody AnimeDTO dto) {
        log.info("updeting anime");
        AnimeDTO anime = animeService.update(dto);
        return new ResponseEntity<>(anime, HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
