package academy.devdojo.springboot2.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimeDTO {

    private Long id;
    @NotEmpty(message = "Name field can't be null")
    @NotNull(message = "Name field can't be empty" )
    private String name;
    private LocalDateTime created;
}
