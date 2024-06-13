package sn.ouznoreyni.bookcatalogservice.shared;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.CreateBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.CreateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.CreatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Author;
import sn.ouznoreyni.bookcatalogservice.entity.Book;
import sn.ouznoreyni.bookcatalogservice.entity.Genre;
import sn.ouznoreyni.bookcatalogservice.repository.AuthorRepository;
import sn.ouznoreyni.bookcatalogservice.repository.BookRepository;
import sn.ouznoreyni.bookcatalogservice.repository.GenreRepository;
import sn.ouznoreyni.bookcatalogservice.service.BookService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@Component
public class DataLoader implements CommandLineRunner {
    private final BookService bookService;
    private final Faker faker;
    private final int COUNT=15;
    @Autowired
    public DataLoader(BookService bookService) {
        this.bookService = bookService;
        this.faker = new Faker(Locale.US); // Use User  locale for user data
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < COUNT; i++) {
            CreateAuthorDTO authorDTO = createRandomAuthorDTO();
            CreateGenreDTO genreDTO = createRandomGenreDTO();
            CreatePublisherDTO publisherDTO = createRandomPublisherDTO();

            CreateBookDTO createBookDTO = CreateBookDTO.builder()
                    .title(faker.book().title())
                    .isbn(faker.number().digits(13))
                    .publishedDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .status(BookStatus.AVAILABLE)
                    .author(authorDTO)
                    .genre(genreDTO)
                    .publisher(publisherDTO)
                    .build();
            bookService.createBook(createBookDTO);
        }

    }


    private CreateAuthorDTO createRandomAuthorDTO() {
        return new CreateAuthorDTO(
                faker.book().author(),
                faker.lorem().paragraph(),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }

    private CreateGenreDTO createRandomGenreDTO() {
        return new CreateGenreDTO(
                faker.book().genre(),
                faker.lorem().sentence()
        );
    }

    private CreatePublisherDTO createRandomPublisherDTO() {
        return new CreatePublisherDTO(
                faker.company().name(),
                faker.address().fullAddress(),
                faker.phoneNumber().cellPhone()
        );
    }

}
