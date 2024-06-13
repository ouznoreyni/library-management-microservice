package sn.ouznoreyni.bookcatalogservice.dto.book;

import lombok.*;
import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListBookDTO {
    private Long id;
    private String title;
    private String isbn;
    private String imageCoverUrl;
    private BookStatus status;

}
