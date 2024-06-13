package sn.ouznoreyni.bookcatalogservice.dto.book;

import lombok.*;
import sn.ouznoreyni.bookcatalogservice.dto.author.AuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.GenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.PublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.review.ReviewDTO;
import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {
    private Long id;
    private String title;
    private String imageCoverUrl;
    private String isbn;
    private LocalDate publishedDate;
    private BookStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthorDTO author;
    private GenreDTO genre;
    private PublisherDTO publisher;
    private List<ReviewDTO> reviews;
}
