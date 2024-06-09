package sn.ouznoreyni.bookcatalogservice.dto.book;

import sn.ouznoreyni.bookcatalogservice.dto.author.AuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.GenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.PublisherDTO;
import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookDTO(
        Long id,
        String title,
        String isbn,
        LocalDate publishedDate,
        BookStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        AuthorDTO author,
        GenreDTO genre,
        PublisherDTO publisher
) {
}
