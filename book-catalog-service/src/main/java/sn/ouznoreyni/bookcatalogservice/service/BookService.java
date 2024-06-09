package sn.ouznoreyni.bookcatalogservice.service;

import sn.ouznoreyni.bookcatalogservice.dto.book.BookCreateDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookListDTO;
import sn.ouznoreyni.bookcatalogservice.dto.book.BookUpdateDTO;
import sn.ouznoreyni.bookcatalogservice.exception.BadRequestException;
import sn.ouznoreyni.bookcatalogservice.exception.ResourceNotFoundException;

import java.util.List;

public interface BookService {
    List<BookListDTO> getAllBooks();
    BookDTO getBookById(Long id) throws ResourceNotFoundException;
    BookDTO createBook(BookCreateDTO bookCreateDTO) throws BadRequestException;
    BookDTO updateBook(Long id, BookUpdateDTO bookUpdateDTO) throws BadRequestException ;
    void deleteBook(Long id) throws ResourceNotFoundException ;
}
