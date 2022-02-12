package academy.devdojo.springboot2.mapper;


import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimeDTO;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@NoArgsConstructor
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimeDTO dto);

}
