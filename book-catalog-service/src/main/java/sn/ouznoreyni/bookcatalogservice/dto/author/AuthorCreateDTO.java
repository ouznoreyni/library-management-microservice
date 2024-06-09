package sn.ouznoreyni.bookcatalogservice.dto.author;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AuthorCreateDTO(
        @NotBlank(message = "Le nom de l'auteur est obligatoire")
        String name,
        @NotBlank(message = "le biographie de l'auteur est obligatoire")
        String biography,

        @NotBlank(message = "la date de naissance de l'auteur est obligatoire")
        LocalDate dateOfBirth
) {}