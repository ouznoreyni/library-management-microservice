package sn.ouznoreyni.bookcatalogservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ouznoreyni.bookcatalogservice.dto.author.AuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.ListAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.author.UpdateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.bookcatalogservice.service.AuthorService;
import sn.ouznoreyni.bookcatalogservice.shared.ApiResponse;

import java.util.List;

/**
 * Controller class for managing authors.
 */
@RestController
@RequestMapping("/api/v1/authors")
@Api(tags = "Author Management")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Retrieves a page of authors.
     *
     * @param pageable The pagination information.
     * @return A {@link ResponseEntity} containing an {@link ApiResponse} with a {@link Page} of {@link AuthorDTO} objects.
     */
    @GetMapping
    @ApiOperation(value = "Get all authors", notes = "Retrieve a paginated list of all authors.")
    public ResponseEntity<ApiResponse<List<ListAuthorDTO>>> getAllAuthors(Pageable pageable) {
        var authors = authorService.getAllAuthors(pageable);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Success", authors.getContent());
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves an author by ID.
     *
     * @param id The ID of the author to retrieve.
     * @return A {@link ResponseEntity} containing an {@link ApiResponse} with the {@link AuthorDTO} representation of the author.
     * @throws ResourceNotFoundException if no author exists with the given ID.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get author by ID", notes = "Retrieve an author by ID.")
    public ResponseEntity<ApiResponse<AuthorDTO>> getAuthorById(@PathVariable Long id) {
        AuthorDTO author = authorService.getAuthorById(id);
        ApiResponse<AuthorDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Success", author);
        return ResponseEntity.ok(response);
    }

    /**
     * Creates a new author.
     *
     * @param createAuthorDTO The data to create the author.
     * @return A {@link ResponseEntity} containing an {@link ApiResponse} with the {@link AuthorDTO} representation of the created author.
     */
    @PostMapping
    @ApiOperation(value = "Create author", notes = "Create a new author.")
    public ResponseEntity<ApiResponse<AuthorDTO>> createAuthor(@RequestBody CreateAuthorDTO createAuthorDTO) {
        AuthorDTO createdAuthor = authorService.createAuthor(createAuthorDTO);
        ApiResponse<AuthorDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Author created successfully", createdAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates an existing author.
     *
     * @param id              The ID of the author to update.
     * @param updateAuthorDTO The updated data for the author.
     * @return A {@link ResponseEntity} containing an {@link ApiResponse} with the {@link AuthorDTO} representation of the updated author.
     * @throws ResourceNotFoundException if no author exists with the given ID.
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update author", notes = "Update an existing author.")
    public ResponseEntity<ApiResponse<AuthorDTO>> updateAuthor(@PathVariable Long id, @RequestBody UpdateAuthorDTO updateAuthorDTO) {
        AuthorDTO updatedAuthor = authorService.updateAuthor(id, updateAuthorDTO);
        ApiResponse<AuthorDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Author updated successfully", updatedAuthor);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes an author by ID.
     *
     * @param id The ID of the author to delete.
     * @return A {@link ResponseEntity} with status {@code 204 NO_CONTENT} if successful.
     * @throws ResourceNotFoundException if no author exists with the given ID.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete author", notes = "Delete an author by ID.")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
