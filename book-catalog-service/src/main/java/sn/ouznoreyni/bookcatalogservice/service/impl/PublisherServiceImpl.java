package sn.ouznoreyni.bookcatalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.CreatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.ListPublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.PublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.UpdatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Publisher;
import sn.ouznoreyni.bookcatalogservice.exception.BadRequestException;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.bookcatalogservice.mapper.PublisherMapper;
import sn.ouznoreyni.bookcatalogservice.repository.PublisherRepository;
import sn.ouznoreyni.bookcatalogservice.service.PublisherService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of {@link PublisherService} that handles operations related to publishers.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);

    private final PublisherRepository publisherRepository;

    private final PublisherMapper publisherMapper;

    /**
     * Creates a new publisher.
     *
     * @param createPublisherDTO DTO containing details of the publisher to be created.
     * @return DTO representing the newly created publisher.
     */
    @Override
    public PublisherDTO createPublisher(CreatePublisherDTO createPublisherDTO) {
        logger.debug("Creating new publisher with name: {}", createPublisherDTO.getName());
        var publisher=publisherMapper.toPublisherEntity(createPublisherDTO);
        publisher = publisherRepository.save(publisher);
        logger.debug("Publisher created successfully with ID: {}", publisher.getId());
        return publisherMapper.toPublisherDTO(publisher);
    }

    /**
     * Retrieves a publisher by its ID.
     *
     * @param id ID of the publisher to retrieve.
     * @return DTO representing the publisher.
     * @throws ResourceNotFoundException if no publisher exists with the given ID.
     */
    @Override
    public PublisherDTO getPublisherById(Long id) {
        logger.debug("Fetching publisher by ID: {}", id);

        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Éditeur non trouvé avec l'ID : %d", id);
                    logger.error(message);
                    return new ResourceNotFoundException(message);
                });

        logger.debug("Publisher fetched successfully: {}", publisher);
        return publisherMapper.toPublisherDTO(publisher);
    }

    /**
     * Retrieves all publishers.
     *
     * @return page of DTOs representing all publishers.
     */
    @Override
    public Page<ListPublisherDTO> getAllPublishers(Pageable pageable) {
        logger.debug("Fetching all publishers.");
        return publisherRepository.findAll(pageable).map(publisherMapper::toPublisherListDTO);
    }

    /**
     * Updates an existing publisher.
     *
     * @param id                 ID of the publisher to update.
     * @param updatePublisherDTO DTO containing updated details of the publisher.
     * @return DTO representing the updated publisher.
     * @throws ResourceNotFoundException if no publisher exists with the given ID.
     */
    @Override
    public PublisherDTO updatePublisher(Long id, UpdatePublisherDTO updatePublisherDTO) {
        logger.debug("Updating publisher with ID: {}", id);

        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Éditeur non trouvé avec l'ID : %d", id);
                    logger.error(message);
                    return new ResourceNotFoundException(message);
                });

        publisher.setName(updatePublisherDTO.getName());
        publisher.setAddress(updatePublisherDTO.getAddress());
        publisher.setContactNumber(updatePublisherDTO.getContactNumber());

        publisher.setUpdatedAt(LocalDateTime.now());
        publisher = publisherRepository.save(publisher);

        logger.debug("Publisher updated successfully: {}", publisher);
        return publisherMapper.toPublisherDTO(publisher);
    }

    /**
     * Deletes a publisher by its ID.
     *
     * @param id ID of the publisher to delete.
     * @throws ResourceNotFoundException if no publisher exists with the given ID.
     */
    @Override
    public void deletePublisher(Long id) {
        logger.debug("Deleting publisher with ID: {}", id);

        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Éditeur non trouvé avec l'ID : %d", id);
                    logger.error(message);
                    return new ResourceNotFoundException(message);
                });

        publisherRepository.delete(publisher);
        logger.debug("Publisher deleted successfully.");
    }

}