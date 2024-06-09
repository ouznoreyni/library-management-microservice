package sn.ouznoreyni.bookcatalogservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ouznoreyni.bookcatalogservice.entity.Publisher;

import java.util.Optional;

@RepositoryRestResource
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByNameIgnoreCase(String name);
}