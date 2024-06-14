package sn.ouznoreyni.bookcatalogservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.CreatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.ListPublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.PublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.UpdatePublisherDTO;

import java.util.List;

@Repository
public interface PublisherService {
    PublisherDTO createPublisher(CreatePublisherDTO createPublisherDTO);
    PublisherDTO getPublisherById(Long id);
    Page<ListPublisherDTO> getAllPublishers(Pageable pageable);
    PublisherDTO updatePublisher(Long id, UpdatePublisherDTO updatePublisherDTO);
    void deletePublisher(Long id);
}
