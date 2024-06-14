package sn.ouznoreyni.bookcatalogservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.CreatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.ListPublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.PublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.UpdatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.service.PublisherService;
import sn.ouznoreyni.bookcatalogservice.shared.ApiResponse;

import java.util.List;

/**
 * REST controller for managing publishers.
 */
@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {

    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * Endpoint to create a new publisher.
     *
     * @param createPublisherDTO DTO containing details of the publisher to be created.
     * @return ResponseEntity containing ApiResponse with status, message, and data (created publisher DTO).
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PublisherDTO>> createPublisher(
            @RequestBody @Validated CreatePublisherDTO createPublisherDTO) {
        logger.debug("Received request to create a new publisher with name: {}", createPublisherDTO.getName());

        PublisherDTO createdPublisher = publisherService.createPublisher(createPublisherDTO);

        ApiResponse<PublisherDTO> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Éditeur créé avec succès",
                createdPublisher
        );

        logger.debug("Publisher created successfully with ID: {}", createdPublisher.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * Endpoint to fetch a publisher by ID.
     *
     * @param id ID of the publisher to fetch.
     * @return ResponseEntity containing ApiResponse with status, message, and data (publisher DTO).
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherDTO>> getPublisherById(@PathVariable Long id) {
        logger.debug("Received request to fetch publisher with ID: {}", id);

        PublisherDTO publisherDTO = publisherService.getPublisherById(id);

        ApiResponse<PublisherDTO> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Éditeur récupéré avec succès",
                publisherDTO
        );

        logger.debug("Publisher fetched successfully with ID: {}", publisherDTO.getId());
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Endpoint to fetch all publishers with pagination support.
     *
     * @param pageable Pagination information.
     * @return ResponseEntity containing ApiResponse with status, message, and data (page of publisher DTOs).
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ListPublisherDTO>>> getAllPublishers(Pageable pageable) {
        logger.debug("Received request to fetch all publishers");

        Page<ListPublisherDTO> publishersPage = publisherService.getAllPublishers(pageable);

        ApiResponse<Page<ListPublisherDTO>> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Liste des éditeurs récupérée avec succès",
                publishersPage
        );

        logger.debug("Fetched {} publishers", publishersPage.getTotalElements());
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Endpoint to update an existing publisher.
     *
     * @param id                  ID of the publisher to update.
     * @param updatePublisherDTO DTO containing updated details of the publisher.
     * @return ResponseEntity containing ApiResponse with status, message, and data (updated publisher DTO).
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherDTO>> updatePublisher(
            @PathVariable Long id,
            @RequestBody @Validated UpdatePublisherDTO updatePublisherDTO) {
        logger.debug("Received request to update publisher with ID: {}", id);

        PublisherDTO updatedPublisher = publisherService.updatePublisher(id, updatePublisherDTO);

        ApiResponse<PublisherDTO> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Éditeur mis à jour avec succès",
                updatedPublisher
        );

        logger.debug("Publisher updated successfully with ID: {}", updatedPublisher.getId());
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Endpoint to delete a publisher by ID.
     *
     * @param id ID of the publisher to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        logger.debug("Received request to delete publisher with ID: {}", id);

        publisherService.deletePublisher(id);

        logger.debug("Publisher deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    // Other endpoints (e.g., search, additional operations) can be added as needed
}
