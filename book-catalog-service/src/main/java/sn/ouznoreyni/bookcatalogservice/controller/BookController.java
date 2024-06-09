package sn.ouznoreyni.bookcatalogservice.controller;


import sn.ouznoreyni.bookcatalogservice.dto.book.BookCreateDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookListDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookUpdateDTO;
import sn.ouznoreyni.bookcatalogservice.exception.BadRequestException;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.bookcatalogservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    /**
     * Get all books
     *
     * @return List of BookListDTO containing basic information about each book
     */
    @GetMapping
    public ResponseEntity<List<BookListDTO>> getAllBooks() {
        List<BookListDTO> books = bookService.getAllBooks();
        books.add(new BookListDTO(1L,"titre", "authorName", "genreName", BookStatus.AVAILABLE ));
        return ResponseEntity.ok(books);
    }

    /**
     * Get a book by its ID
     *
     * @param id ID of the book
     * @return BookDTO containing detailed information about the book
     * @throws ResourceNotFoundException if the book with the given ID is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    /**
     * Create a new book
     *
     * @param bookCreateDTO Data for creating the book
     * @return BookDTO containing detailed information about the created book
     * @throws BadRequestException if any required data is missing or if author, genre, or publisher is not found
     */
    @PostMapping(params = "version=1")
    public ResponseEntity<BookDTO> createBookV1(@RequestBody BookCreateDTO bookCreateDTO) throws BadRequestException {
        BookDTO createdBook = bookService.createBook(bookCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PostMapping(params = "version=2")
    public ResponseEntity<BookDTO> createBookV2(@RequestBody BookCreateDTO bookCreateDTO) throws BadRequestException {
        // Implement version 2 logic here
       return null;
    }

    // Similarly, implement versioned endpoints for other operations like update and delete
}
