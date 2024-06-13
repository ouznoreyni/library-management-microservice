package sn.ouznoreyni.bookcatalogservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ouznoreyni.bookcatalogservice.dto.author.AuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.ListAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.UpdateAuthorDTO;

public interface AuthorService {
    Page<ListAuthorDTO> getAllAuthors(Pageable pageable);

    AuthorDTO getAuthorById(Long id);

    AuthorDTO createAuthor(CreateAuthorDTO createAuthorDTO);

    AuthorDTO updateAuthor(Long id, UpdateAuthorDTO updateAuthorDTO);

    void deleteAuthor(Long id);
}
