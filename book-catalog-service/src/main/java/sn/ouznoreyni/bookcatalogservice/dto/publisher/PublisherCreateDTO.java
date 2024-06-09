package sn.ouznoreyni.bookcatalogservice.dto.publisher;

import jakarta.validation.constraints.NotBlank;


public record PublisherCreateDTO(
        @NotBlank(message = "Le nom de l'éditeur est obligatoire")
        String name,

        @NotBlank(message = "L'adresse de l'éditeur est obligatoire")
        String address,

        @NotBlank(message = "Le contacr de l'éditeur est obligatoire")
        String contactNumber

) {
}


