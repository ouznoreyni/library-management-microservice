package sn.ouznoreyni.bookcatalogservice.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.UpdateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.CreateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.GenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.ListGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.UpdateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Genre;


@Component
@AllArgsConstructor
public class GenreMapper {
    private final ModelMapper modelMapper;

    public GenreDTO toGenreDTO(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }


    public Genre toGenreEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }

    public Genre toGenreEntity(CreateGenreDTO createGenreDTO) {
        return modelMapper.map(createGenreDTO, Genre.class);
    }

    public Genre toGenreEntity(UpdateGenreDTO updateGenreDTO) {
        return modelMapper.map(updateGenreDTO, Genre.class);
    }

    public ListGenreDTO toGenreListDTO(Genre genre) {
        return ListGenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }


}
