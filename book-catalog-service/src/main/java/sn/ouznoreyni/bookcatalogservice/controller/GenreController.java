package sn.ouznoreyni.bookcatalogservice.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sn.ouznoreyni.bookcatalogservice.dto.genre.CreateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.GenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.ListGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.UpdateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.service.GenreService;
import sn.ouznoreyni.bookcatalogservice.shared.ApiResponse;

import java.util.List;
import java.util.prefs.BackingStoreException;

@RestController
@RequestMapping("/api/v1/genres")
@Validated
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Endpoint to create a new genre.
     *
     * @param createGenreDTO DTO containing genre information to create
     * @return ResponseEntity containing ApiResponse with status, message, and data (created genre DTO)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<GenreDTO>> createGenre(@RequestBody @Valid CreateGenreDTO createGenreDTO) {
        GenreDTO createdGenre = genreService.createGenre(createGenreDTO);
        ApiResponse<GenreDTO> response = ApiResponse.<GenreDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Genre créé avec succès")
                .data(createdGenre)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint to retrieve all genres.
     *
     * @param pageable Pagination information
     * @return ResponseEntity containing ApiResponse with status, message, and data (list of genre DTOs)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ListGenreDTO>>> getAllGenres(Pageable pageable) {
        var genres = genreService.getAllGenres(pageable);
        var response = ApiResponse.<List<ListGenreDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Liste de tous les genres récupérée avec succès")
                .data(genres.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to retrieve a genre by its ID.
     *
     * @param id Genre ID
     * @return ResponseEntity containing ApiResponse with status, message, and data (genre DTO)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GenreDTO>> getGenreById(@PathVariable Long id) {
        GenreDTO genreDTO = genreService.getGenreById(id);
        ApiResponse<GenreDTO> response = ApiResponse.<GenreDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Genre récupéré avec succès")
                .data(genreDTO)
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to update a genre.
     *
     * @param id             Genre ID
     * @param updateGenreDTO DTO containing updated genre information
     * @return ResponseEntity containing ApiResponse with status, message, and data (updated genre DTO)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GenreDTO>> updateGenre(
            @PathVariable Long id,
            @RequestBody @Valid UpdateGenreDTO updateGenreDTO) {
        GenreDTO updatedGenre = genreService.updateGenre(id, updateGenreDTO);
        ApiResponse<GenreDTO> response = ApiResponse.<GenreDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Genre mis à jour avec succès")
                .data(updatedGenre)
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to delete a genre by its ID.
     *
     * @param id Genre ID
     * @return ResponseEntity containing ApiResponse with status and message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGenre(@PathVariable Long id) throws BackingStoreException {
        genreService.deleteGenre(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Genre supprimé avec succès")
                .build();
        return ResponseEntity.ok(response);
    }
}
