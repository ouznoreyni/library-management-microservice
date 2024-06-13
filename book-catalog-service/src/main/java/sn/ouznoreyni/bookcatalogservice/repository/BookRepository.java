package sn.ouznoreyni.bookcatalogservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sn.ouznoreyni.bookcatalogservice.entity.Book;

import java.time.LocalDateTime;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Page<Book> findByTitleContainingIgnoreCaseAndIsbnContainingIgnoreCaseAndAuthor_NameContainingIgnoreCaseAndGenre_NameContainingIgnoreCaseAndPublisher_NameContainingIgnoreCaseAndCreatedAtAfterAndCreatedAtBefore(
            String title, String isbn, String authorName, String genreName, String publisherName,
            LocalDateTime publishedAfter, LocalDateTime publishedBefore, Pageable pageable);
}