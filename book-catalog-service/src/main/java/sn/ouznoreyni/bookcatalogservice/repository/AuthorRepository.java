package sn.ouznoreyni.bookcatalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ouznoreyni.bookcatalogservice.entity.Author;

import java.util.Optional;

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameIgnoreCase(String name);
}