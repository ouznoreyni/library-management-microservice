package sn.ouznoreyni.bookcatalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ouznoreyni.bookcatalogservice.entity.Genre;

import java.util.Optional;

@RepositoryRestResource
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByNameIgnoreCase(String name);
}