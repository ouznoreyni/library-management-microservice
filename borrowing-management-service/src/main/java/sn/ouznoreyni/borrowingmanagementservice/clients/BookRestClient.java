package sn.ouznoreyni.borrowingmanagementservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.ouznoreyni.borrowingmanagementservice.dto.book.Book;

import java.util.List;
import java.util.Optional;

@FeignClient("BOOK-CATALOG-SERVICE")
public interface BookRestClient {

    @GetMapping("/api/v1/books/{id}")
    @CircuitBreaker(name = "booksService", fallbackMethod = "getDefaultbook")
    Optional<Book> getBookById(@PathVariable Long id);


    default Optional<Book> getDefaultbook(Long id, Exception e){
        return Optional.empty();
    }
    default List<Book> getDefaultBooks(Exception e){
        return List.of();
    }
}
