package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.dto.AnimeDTO;
import academy.devdojo.springboot2.service.AnimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Sumary of peration of get method ",description = "Descriptio of get method ")
    public ResponseEntity<List<AnimeDTO>> list(){

        return ResponseEntity.ok().body(animeService.findAll());
    }



    @GetMapping(value = "/pageable")
    public ResponseEntity<Page<AnimeDTO>> page(@ParameterObject() Pageable pageable){

        return ResponseEntity.ok().body(animeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "When successful"),
            @ApiResponse(responseCode = "404", description = "When not found"),
    })
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

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
