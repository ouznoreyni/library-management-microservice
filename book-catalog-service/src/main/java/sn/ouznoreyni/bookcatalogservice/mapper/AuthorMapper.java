package sn.ouznoreyni.bookcatalogservice.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.bookcatalogservice.dto.author.AuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.ListAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.UpdateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Author;


@Component
@AllArgsConstructor
public class AuthorMapper {
    private final ModelMapper modelMapper;

    public AuthorDTO toAuthorDTO(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }

    public Author toAuthorEntity(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }

    public Author toAuthorEntity(CreateAuthorDTO createAuthorDTO) {
        return modelMapper.map(createAuthorDTO, Author.class);
    }

    public Author toAuthorEntity(UpdateAuthorDTO updateAuthorDTO) {
        return modelMapper.map(updateAuthorDTO, Author.class);
    }

    public ListAuthorDTO toAuthorListDTO(Author author) {
        return ListAuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }


}
