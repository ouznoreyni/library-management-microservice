package sn.ouznoreyni.bookcatalogservice.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.CreateBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.ListBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.UpdateBookDTO;
import sn.ouznoreyni.bookcatalogservice.exception.BadRequestException;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.bookcatalogservice.service.BookService;
import sn.ouznoreyni.bookcatalogservice.shared.CatalogServiceUtils;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    /**
     * Get all books with pagination, sorting, and optional filtering
     *
     * @param page            Page number (default: 0)
     * @param size            Number of items per page (default: 10, max: 50)
     * @param sortBy          Field to sort by (optional: title, isbn, createdAt, updatedAt, author.name, genre.name, publisher.name)
     * @param sortDesc        Sort direction (optional: asc, desc)
     * @param title           Filter by book title (optional)
     * @param isbn            Filter by book ISBN (optional)
     * @param publishedAfter  Filter books published after a given date (optional)
     * @param publishedBefore Filter books published before a given date (optional)
     * @return ResponseEntity with paginated list of books
     */
    @GetMapping
    public ResponseEntity<Page<ListBookDTO>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String genreName,
            @RequestParam(required = false) String publisherName,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String sortDesc,
            @RequestParam(required = false) String publishedAfter,
            @RequestParam(required = false) String publishedBefore
    ) {
        logger.debug("Fetching all books with pagination and filters. Pageable: {}, Title: {}, ISBN: {}, Author: {}, Genre: {}, Publisher: {}, PublishedAfter: {}, PublishedBefore: {}",
                PageRequest.of(page, size, CatalogServiceUtils.buildSort(sort, sortDesc)),
                title, isbn, authorName, genreName, publisherName, publishedAfter, publishedBefore);

        Pageable pageable = PageRequest.of(page, size, CatalogServiceUtils.buildSort(sort, sortDesc));
        Page<ListBookDTO> books = bookService.getAllBooks(pageable, title, isbn, authorName, genreName, publisherName, CatalogServiceUtils.parseDateTime(publishedAfter), CatalogServiceUtils.parseDateTime(publishedBefore));

        logger.debug("Fetched {} books successfully.", books.getTotalElements());

        return ResponseEntity.ok().body(books);
    }

    /**
     * Get a book by its ID
     *
     * @param id ID of the book
     * @return ResponseEntity with BookDTO containing detailed information about the book
     * @throws BadRequestException if the book with the given ID is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) throws BadRequestException {
        logger.debug("Request to get book by ID: {}", id);
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    /**
     * Create a new book
     *
     * @param createBookDTO Data for creating the book
     * @return ResponseEntity with BookDTO containing detailed information about the created book
     * @throws BadRequestException if any required data is missing or if author, genre, or publisher is not found
     */
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookDTO createBookDTO) throws BadRequestException {
        logger.debug("Request to create a new book: {}", createBookDTO);
        BookDTO createdBook = bookService.createBook(createBookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    /**
     * Update an existing book
     *
     * @param id            ID of the book to update
     * @param updateBookDTO Data for updating the book
     * @return ResponseEntity with BookDTO containing detailed information about the updated book
     * @throws ResourceNotFoundException if the book with the given ID is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody UpdateBookDTO updateBookDTO) throws ResourceNotFoundException {
        logger.debug("Request to update book with ID {}: {}", id, updateBookDTO);
        BookDTO updatedBook = bookService.updateBook(id, updateBookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Delete a book by its ID
     *
     * @param id ID of the book to delete
     * @return ResponseEntity with HTTP status OK if deletion is successful
     * @throws ResourceNotFoundException if the book with the given ID is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws ResourceNotFoundException {
        logger.debug("Request to delete book with ID: {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }


}
