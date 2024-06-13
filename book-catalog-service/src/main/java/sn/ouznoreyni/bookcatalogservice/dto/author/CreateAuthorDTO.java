package sn.ouznoreyni.bookcatalogservice.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAuthorDTO {
    @NotBlank(message = "Le nom de l'auteur est obligatoire")
    private String name;

    @NotBlank(message = "le biographie de l'auteur est obligatoire")
    private String biography;

    @NotNull(message = "la date de naissance de l'auteur est nulle")
    private LocalDate dateOfBirth;
}