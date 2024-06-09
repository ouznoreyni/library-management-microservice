package sn.ouznoreyni.bookcatalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ouznoreyni.bookcatalogservice.entity.Book;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {
}