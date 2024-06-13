package sn.ouznoreyni.bookcatalogservice.dto.genre;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateGenreDTO {
    @NotBlank(message = "Le nom du genre est obligatoire")
    private String name;
    @NotBlank(message = "La description du genre est obligatoire")
    private String description;

}
