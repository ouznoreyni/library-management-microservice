package sn.ouznoreyni.bookcatalogservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ouznoreyni.bookcatalogservice.dto.genre.CreateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.GenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.ListGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.UpdateGenreDTO;

import java.util.prefs.BackingStoreException;

public interface GenreService {
    GenreDTO createGenre(CreateGenreDTO createGenreDTO);

    GenreDTO getGenreById(Long id);

    Page<ListGenreDTO> getAllGenres(Pageable pageable);

    GenreDTO updateGenre(Long id, UpdateGenreDTO updateGenreDTO);

    void deleteGenre(Long id) throws BackingStoreException;
}