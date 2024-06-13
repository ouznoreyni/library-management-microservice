package sn.ouznoreyni.bookcatalogservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.ouznoreyni.bookcatalogservice.dto.author.AuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.ListAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.UpdateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Author;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.bookcatalogservice.mapper.AuthorMapper;
import sn.ouznoreyni.bookcatalogservice.repository.AuthorRepository;
import sn.ouznoreyni.bookcatalogservice.service.AuthorService;

/**
 * Implementation of {@link AuthorService} that provides CRUD operations for authors.
 */
@Service
@Transactional
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final ModelMapper modelMapper;


    /**
     * Retrieves a page of authors.
     *
     * @param pageable The pagination information.
     * @return A {@link Page} of {@link AuthorDTO} objects.
     */
    @Override
    public Page<ListAuthorDTO> getAllAuthors(Pageable pageable) {
        logger.info("Fetching authors with pageable {}", pageable);
        var authorPage = authorRepository.findAll(pageable).map(authorMapper::toAuthorListDTO);
        return authorPage;
    }

    /**
     * Retrieves an author by ID.
     *
     * @param id The ID of the author to retrieve.
     * @return The {@link AuthorDTO} representation of the author.
     * @throws ResourceNotFoundException if no author exists with the given ID.
     */
    @Override
    public AuthorDTO getAuthorById(Long id) {
        logger.info("Fetching author with id {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auteur avec l'identifiant " + id + " n'existe pas"));
        return authorMapper.toAuthorDTO(author);
    }

    /**
     * Creates a new author.
     *
     * @param createAuthorDTO The data to create the author.
     * @return The {@link AuthorDTO} representation of the created author.
     */
    @Override
    public AuthorDTO createAuthor(CreateAuthorDTO createAuthorDTO) {
        logger.info("Creating new author: {}", createAuthorDTO);
        var author = authorMapper.toAuthorEntity(createAuthorDTO);
        author = authorRepository.save(author);
        return authorMapper.toAuthorDTO(author);
    }

    /**
     * Updates an existing author.
     *
     * @param id              The ID of the author to update.
     * @param updateAuthorDTO The updated data for the author.
     * @return The {@link AuthorDTO} representation of the updated author.
     * @throws ResourceNotFoundException if no author exists with the given ID.
     */
    @Override
    public AuthorDTO updateAuthor(Long id, UpdateAuthorDTO updateAuthorDTO) {
        logger.info("Updating author with id {} with data: {}", id, updateAuthorDTO);
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auteur avec l'identifiant " + id + " n'existe pas"));

        author.setName(updateAuthorDTO.getName());
        author.setBiography(updateAuthorDTO.getBiography());
        author.setDateOfBirth(updateAuthorDTO.getDateOfBirth());

        author = authorRepository.save(author);
        return authorMapper.toAuthorDTO(author);
    }

    /**
     * Deletes an author by ID.
     *
     * @param id The ID of the author to delete.
     * @throws ResourceNotFoundException if no author exists with the given ID.
     */
    @Override
    public void deleteAuthor(Long id) {
        logger.info("Deleting author with id {}", id);
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Auteur avec l'identifiant " + id + " n'existe pas");
        }
        authorRepository.deleteById(id);
    }
}

