package sn.ouznoreyni.bookcatalogservice.dto.author;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AuthorUpdateDTO(
        @NotBlank(message = "Le nom de l'auteur est obligatoire")
        String name,
        String biography,
        LocalDate dateOfBirth
) {
}
