package sn.ouznoreyni.bookcatalogservice.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

import java.time.LocalDate;


public record BookCreateDTO(
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

        @NotBlank(message = "Le nom de l'auteur est obligatoire")
        String authorName,

        @NotBlank(message = "Le nom du genre est obligatoire")
        String genreName,

        @NotBlank(message = "Le nom de l'éditeur est obligatoire")
        String publisherName
) {}
