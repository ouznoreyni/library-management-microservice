package sn.ouznoreyni.bookcatalogservice.dto.book;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

import java.time.LocalDate;

public record BookUpdateDTO(
        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 255, message = "Le titre ne peut pas dépasser 255 caractères")
        String title,

        @NotBlank(message = "L'ISBN est obligatoire")
        @Size(max = 13, message = "L'ISBN ne peut pas dépasser 13 caractères")
        String isbn,

        @NotNull(message = "La date de publication est obligatoire")
        LocalDate publishedDate,

        @NotNull(message = "Le statut est obligatoire")
        BookStatus status,

        @NotNull(message = "L'ID de l'auteur est obligatoire")
        Long authorId,

        @NotNull(message = "L'ID du genre est obligatoire")
        Long genreId,

        @NotNull(message = "L'ID de l'éditeur est obligatoire")
        Long publisherId
) {}