package sn.ouznoreyni.borrowingmanagementservice.dto.book;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private Long id;
    private String title;
    private String imageCoverUrl;
    private String isbn;
    private LocalDate publishedDate;
   // private BookStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
   // private AuthorDTO author;
   // private GenreDTO genre;
  //  private PublisherDTO publisher;
   // private List<ReviewDTO> reviews;
}
