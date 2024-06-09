package sn.ouznoreyni.bookcatalogservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookCreateDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookListDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookUpdateDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Author;
import sn.ouznoreyni.bookcatalogservice.entity.Book;
import sn.ouznoreyni.bookcatalogservice.entity.Genre;
import sn.ouznoreyni.bookcatalogservice.entity.Publisher;
import sn.ouznoreyni.bookcatalogservice.exception.BadRequestException;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.bookcatalogservice.mapper.BookMapper;
import sn.ouznoreyni.bookcatalogservice.repository.AuthorRepository;
import sn.ouznoreyni.bookcatalogservice.repository.BookRepository;
import sn.ouznoreyni.bookcatalogservice.repository.GenreRepository;
import sn.ouznoreyni.bookcatalogservice.repository.PublisherRepository;
import sn.ouznoreyni.bookcatalogservice.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final BookMapper bookMapper;

    /**
     * Get all books
     *
     * @return List of BookListDTO containing basic information about each book
     */
    @Override
    public List<BookListDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookListDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a book by its ID
     *
     * @param id ID of the book
     * @return BookDTO containing detailed information about the book
     * @throws BadRequestException if the book with the given ID is not found
     */
    @Override
    public BookDTO getBookById(Long id) throws BadRequestException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Livre non trouvé avec l'ID : " + id));
        return bookMapper.toBookDTO(book);
    }

    /**
     * Create a new book
     *
     * @param bookCreateDTO Data for creating the book
     * @return BookDTO containing detailed information about the created book
     * @throws BadRequestException if any required data is missing or if author, genre, or publisher is not found
     */
    @Override
    public BookDTO createBook(BookCreateDTO bookCreateDTO) throws BadRequestException {
        // Find author by name (case-insensitive)
        Author author = authorRepository.findByNameIgnoreCase(bookCreateDTO.authorName())
                .orElseThrow(() -> new BadRequestException("Auteur non trouvé avec le nom : " + bookCreateDTO.authorName()));

        // Find genre by name (case-insensitive)
        Genre genre = genreRepository.findByNameIgnoreCase(bookCreateDTO.genreName())
                .orElseThrow(() -> new BadRequestException("Genre non trouvé avec le nom : " + bookCreateDTO.genreName()));

        // Find publisher by name (case-insensitive)
        Publisher publisher = publisherRepository.findByNameIgnoreCase(bookCreateDTO.publisherName())
                .orElseThrow(() -> new BadRequestException("Editeur non trouvé avec le nom : " + bookCreateDTO.publisherName()));

        // Create the book entity
        Book book = bookMapper.toBookEntity(bookCreateDTO);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublisher(publisher);

        // Save the book to the database
        Book savedBook = bookRepository.save(book);

        // Map the saved book to DTO and return it
        return bookMapper.toBookDTO(savedBook);
    }

    /**
     * Update an existing book
     *
     * @param id            ID of the book to update
     * @param bookUpdateDTO Data for updating the book
     * @return BookDTO containing detailed information about the updated book
     * @throws BadRequestException if the book with the given ID is not found
     */
    @Override
    public BookDTO updateBook(Long id, BookUpdateDTO bookUpdateDTO) throws BadRequestException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Livre non trouvé avec l'ID : " + id));
        book.setTitle(bookUpdateDTO.title());
        book.setIsbn(bookUpdateDTO.isbn());
        book.setPublishedDate(bookUpdateDTO.publishedDate());
        book.setStatus(bookUpdateDTO.status());
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toBookDTO(updatedBook);
    }

    /**
     * Delete a book by its ID
     *
     * @param id ID of the book to delete
     * @throws ResourceNotFoundException if the book with the given ID is not found
     */
    @Override
    public void deleteBook(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre non trouvé avec l'ID : " + id));
        bookRepository.delete(book);
    }
}
