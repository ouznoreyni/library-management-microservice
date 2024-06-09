package sn.ouznoreyni.bookcatalogservice.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.bookcatalogservice.dto.author.AuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookCreateDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookListDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookUpdateDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.GenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.PublisherDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Author;
import sn.ouznoreyni.bookcatalogservice.entity.Book;
import sn.ouznoreyni.bookcatalogservice.entity.Genre;
import sn.ouznoreyni.bookcatalogservice.entity.Publisher;

@Component
@AllArgsConstructor
public class BookMapper {
    private final ModelMapper modelMapper;


    public BookDTO toBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }


    public Book toBookEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public Book toBookEntity(BookCreateDTO bookCreateDTO) {
        return modelMapper.map(bookCreateDTO, Book.class);
    }

    public Book toBookEntity(BookUpdateDTO bookUpdateDTO) {
        return modelMapper.map(bookUpdateDTO, Book.class);
    }

    public BookListDTO toBookListDTO(Book book) {
        return new BookListDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getGenre().getName(),
                book.getStatus()
        );
    }

    public AuthorDTO toAuthorDTO(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }

    public GenreDTO toGenreDTO(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }

    public PublisherDTO toPublisherDTO(Publisher publisher) {
        return modelMapper.map(publisher, PublisherDTO.class);
    }
}
