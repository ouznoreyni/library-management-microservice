package sn.ouznoreyni.bookcatalogservice.dto.book;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import sn.ouznoreyni.bookcatalogservice.dto.author.CreateAuthorDTO;
import sn.ouznoreyni.bookcatalogservice.dto.genre.CreateGenreDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.CreatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateBookDTO {
    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 255, message = "Le titre ne peut pas dépasser 255 caractères")
    private String title;

    @NotBlank(message = "L'ISBN est obligatoire")
    @Size(max = 100, message = "L'ISBN ne peut pas dépasser 100 caractères")
    private String isbn;

    private String imageCoverUrl;

    @NotNull(message = "La date de publication est obligatoire")
    private LocalDate publishedDate;

    @NotNull(message = "Le statut est obligatoire")
    private BookStatus status;

    @NotNull(message = "L'auteur est obligatoire")
    @Valid
    private CreateAuthorDTO author;

    @NotNull(message = "Le genre est obligatoire")
    @Valid
    private CreateGenreDTO genre;

    @NotNull(message = "L'éditeur est obligatoire")
    @Valid
    private CreatePublisherDTO publisher;
}