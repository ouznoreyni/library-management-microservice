package sn.ouznoreyni.bookcatalogservice.dto.genre;

import jakarta.validation.constraints.NotBlank;

public record GenreCreateDTO(
        @NotBlank(message = "Le nom du genre est obligatoire")
        String name,
        @NotBlank(message = "La description du genre est obligatoire")
        String description
) {
}
