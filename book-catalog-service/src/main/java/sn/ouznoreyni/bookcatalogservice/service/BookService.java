package sn.ouznoreyni.bookcatalogservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.CreateBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.ListBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.UpdateBookDTO;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

public interface BookService {
    Page<ListBookDTO> getAllBooks(Pageable pageable, String title, String isbn, String authorName, String genreName, String publisherName, LocalDateTime publishedAfter, LocalDateTime publishedBefore);

    BookDTO getBookById(Long id) throws ResourceNotFoundException;

    BookDTO createBook(CreateBookDTO createBookDTO);

    BookDTO updateBook(Long id, UpdateBookDTO updateBookDTO) throws ResourceNotFoundException;

    void deleteBook(Long id) throws ResourceNotFoundException;
}
