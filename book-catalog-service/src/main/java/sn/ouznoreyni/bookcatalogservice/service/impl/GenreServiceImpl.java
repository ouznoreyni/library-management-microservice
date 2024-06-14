package sn.ouznoreyni.bookcatalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ouznoreyni.bookcatalogservice.dto.genre.CreateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.GenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.ListGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.UpdateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Genre;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.bookcatalogservice.mapper.GenreMapper;
import sn.ouznoreyni.bookcatalogservice.repository.GenreRepository;
import sn.ouznoreyni.bookcatalogservice.service.GenreService;

import java.time.LocalDateTime;
import java.util.prefs.BackingStoreException;

/**
 * Implementation of {@link GenreService} providing CRUD operations for genres.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final ModelMapper modelMapper;

    /**
     * Creates a new genre from the data provided in {@code createGenreDTO}.
     *
     * @param createGenreDTO DTO containing genre information to create
     * @return DTO representing the created genre
     */
    @Override
    public GenreDTO createGenre(CreateGenreDTO createGenreDTO) {
        logger.info("Creating a new genre with data: {}", createGenreDTO);
       var genre = genreRepository.save(genreMapper.toGenreEntity(createGenreDTO));
        GenreDTO genreDTO = genreMapper.toGenreDTO(genre);
        logger.info("Genre created successfully: {}", genreDTO);
        return genreDTO;
    }

    /**
     * Retrieves genre information specified by its identifier.
     *
     * @param id genre identifier to retrieve
     * @return DTO representing the retrieved genre
     * @throws ResourceNotFoundException if no genre is found with the specified identifier
     */
    @Override
    @Transactional(readOnly = true)
    public GenreDTO getGenreById(Long id) {
        logger.info("Fetching genre with id: {}", id);

        Genre genre = findGenreById(id);
        GenreDTO genreDTO = genreMapper.toGenreDTO(genre);

        logger.info("Genre retrieved successfully: {}", genreDTO);
        return genreDTO;
    }

    /**
     * Retrieves a list of all available genres.
     *
     * @return List of DTOs representing all genres
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ListGenreDTO> getAllGenres(Pageable pageable) {
        logger.info("Fetching list of all genres pageable: {}", pageable);
        return genreRepository.findAll(pageable).map(genreMapper::toGenreListDTO);
    }

    /**
     * Updates genre information specified by its identifier.
     *
     * @param id             genre identifier to update
     * @param updateGenreDTO DTO containing new genre information
     * @return DTO representing the updated genre
     * @throws ResourceNotFoundException if no genre is found with the specified identifier
     */
    @Override
    public GenreDTO updateGenre(Long id, UpdateGenreDTO updateGenreDTO) {
        logger.info("Updating genre with id: {} with data: {}", id, updateGenreDTO);

        Genre genre = findGenreById(id);
        genre.setName(updateGenreDTO.getName());
        genre.setDescription(updateGenreDTO.getDescription());
        genre.setUpdatedAt(LocalDateTime.now()); // Update modification timestamp
        genre = genreRepository.save(genre);

        GenreDTO genreDTO = genreMapper.toGenreDTO(genre);
        logger.info("Genre updated successfully: {}", genreDTO);
        return genreDTO;
    }

    /**
     * Deletes a genre specified by its identifier.
     *
     * @param id genre identifier to delete
     * @throws ResourceNotFoundException if no genre is found with the specified identifier
     */
    @Override
    public void deleteGenre(Long id) throws BackingStoreException {
        logger.info("Deleting genre with id: {}", id);
        Genre genre = findGenreById(id);
        genreRepository.delete(genre);
        logger.info("Genre deleted successfully: {}", id);
    }

    /**
     * Finds and returns the genre specified by its identifier.
     *
     * @param id genre identifier to search for
     * @return Genre entity corresponding to the identifier
     * @throws ResourceNotFoundException if no genre is found with the specified identifier
     */
    private Genre findGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
    }

}
