package sn.ouznoreyni.bookcatalogservice.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.CreateBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.ListBookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.UpdateBookDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Book;

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

    public Book toBookEntity(CreateBookDTO createBookDTO) {
        return modelMapper.map(createBookDTO, Book.class);
    }

    public Book toBookEntity(UpdateBookDTO updateBookDTO) {
        return modelMapper.map(updateBookDTO, Book.class);
    }

    public ListBookDTO toBookListDTO(Book book) {
        return ListBookDTO.builder()
                .id(book.getId())
                .imageCoverUrl(book.getImageCoverUrl())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .status(book.getStatus())
                .build();
    }

}
