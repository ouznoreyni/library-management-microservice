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
public class UpdateAuthorDTO {
    @NotBlank(message = "Le nom de l'auteur est obligatoire")
    private String name;
    @NotBlank(message = "La biographie est obligatoire")
    private String biography;
    @NotNull(message = "La date de naissance est obligatoire")
    private LocalDate dateOfBirth;
}
