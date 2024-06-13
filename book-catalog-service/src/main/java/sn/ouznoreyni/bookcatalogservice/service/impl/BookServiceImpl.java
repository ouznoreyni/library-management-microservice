package sn.ouznoreyni.bookcatalogservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.CreateBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.ListBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.UpdateBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.CreateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.CreatePublisherDTO;
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
import sn.ouznoreyni.bookcatalogservice.shared.Specifications;

import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final BookMapper bookMapper;
    private final ModelMapper modelMapper;

    /**
     * Get all books
     *
     * @return List of BookDetailsDTO containing basic information about each book
     */
    @Override
    public Page<ListBookDTO> getAllBooks(Pageable pageable, String title, String isbn, String authorName, String genreName, String publisherName, LocalDateTime publishedAfter, LocalDateTime publishedBefore) {
        logger.debug("Fetching all books with pagination and filters. Pageable: {}, Title: {}, ISBN: {}, Author: {}, Genre: {}, Publisher: {}, PublishedAfter: {}, PublishedBefore: {}",
                pageable, title, isbn, authorName, genreName, publisherName, publishedAfter, publishedBefore);
        Page<Book> booksPage = bookRepository.findAll(
                Specifications.buildBookSpecification(title, isbn, authorName, genreName, publisherName, publishedAfter, publishedBefore),
                pageable
        );

        return booksPage.map(bookMapper::toBookListDTO);
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
        logger.debug("Fetching book with ID: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Book not found with ID: {}", id);
                    return new BadRequestException("Livre non trouvé avec l'ID : " + id);
                });
        logger.debug("Fetched book: {}", book);
        return bookMapper.toBookDTO(book);
    }

    /**
     * Create a new book
     *
     * @param createBookDTO Data for creating the book
     * @return BookDTO containing detailed information about the created book
     * @throws BadRequestException if any required data is missing or if author, genre, or publisher is not found
     */
    @Override
    public BookDTO createBook(CreateBookDTO createBookDTO) {
        logger.debug("Creating a new book with data: {}", createBookDTO);
        // Optional: Set matching strategy if needed
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Author author = findOrCreateAuthor(createBookDTO.getAuthor());
        Genre genre = findOrCreateGenre(createBookDTO.getGenre());
        Publisher publisher = findOrCreatePublisher(createBookDTO.getPublisher());

        // Create the book entity
        Book book = bookMapper.toBookEntity(createBookDTO);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublisher(publisher);

        // Save the book to the database
        Book savedBook = bookRepository.save(book);

        logger.info("Created book with ID: {}", savedBook.getId());
        return bookMapper.toBookDTO(savedBook);
    }

    /**
     * Update an existing book
     *
     * @param id            ID of the book to update
     * @param updateBookDTO Data for updating the book
     * @return BookDTO containing detailed information about the updated book
     * @throws BadRequestException if the book with the given ID is not found
     */
    @Override
    public BookDTO updateBook(Long id, UpdateBookDTO updateBookDTO) throws ResourceNotFoundException {
        logger.debug("Updating book with ID: {} using data: {}", id, updateBookDTO);
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            logger.error("Book not found with ID: {}", id);
            return new ResourceNotFoundException("Livre non trouvé");
        });

        modelMapper.map(updateBookDTO, book);

        Author author = findOrCreateAuthor(updateBookDTO.getAuthor());
        Genre genre = findOrCreateGenre(updateBookDTO.getGenre());
        Publisher publisher = findOrCreatePublisher(updateBookDTO.getPublisher());

        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublisher(publisher);

        Book updatedBook = bookRepository.save(book);
        logger.info("Updated book with ID: {}", updatedBook.getId());
        return modelMapper.map(updatedBook, BookDTO.class);
    }

    /**
     * Delete a book by its ID
     *
     * @param id ID of the book to delete
     * @throws ResourceNotFoundException if the book with the given ID is not found
     */
    @Override
    public void deleteBook(Long id) throws ResourceNotFoundException {
        logger.debug("Deleting book with ID: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Book not found with ID: {}", id);
                    return new ResourceNotFoundException("Livre non trouvé avec l'ID : " + id);
                });
        bookRepository.delete(book);
        logger.info("Deleted book with ID: {}", id);
    }

    private Author findOrCreateAuthor(CreateAuthorDTO authorDTO) {
        logger.debug("Finding or creating author: {}", authorDTO);
        return authorRepository.findByNameIgnoreCase(authorDTO.getName()).orElseGet(() -> {
            Author newAuthor = Author.builder()
                    .name(authorDTO.getName())
                    .biography(authorDTO.getBiography())
                    .dateOfBirth(authorDTO.getDateOfBirth())
                    .build();
            Author savedAuthor = authorRepository.save(newAuthor);
            logger.info("Created new author with ID: {}", savedAuthor.getId());
            return savedAuthor;
        });
    }

    private Genre findOrCreateGenre(CreateGenreDTO genreDTO) {
        logger.debug("Finding or creating genre: {}", genreDTO);
        return genreRepository.findByNameIgnoreCase(genreDTO.getName()).orElseGet(() -> {
            Genre newGenre = Genre.builder()
                    .name(genreDTO.getName())
                    .description(genreDTO.getDescription())
                    .build();
            Genre savedGenre = genreRepository.save(newGenre);
            logger.info("Created new genre with ID: {}", savedGenre.getId());
            return savedGenre;
        });
    }

    private Publisher findOrCreatePublisher(CreatePublisherDTO publisherDTO) {
        logger.debug("Finding or creating publisher: {}", publisherDTO);
        return publisherRepository.findByNameIgnoreCase(publisherDTO.getName()).orElseGet(() -> {
            Publisher newPublisher = Publisher.builder()
                    .name(publisherDTO.getName())
                    .address(publisherDTO.getAddress())
                    .contactNumber(publisherDTO.getContactNumber())
                    .build();
            Publisher savedPublisher = publisherRepository.save(newPublisher);
            logger.info("Created new publisher with ID: {}", savedPublisher.getId());
            return savedPublisher;
        });
    }
}
