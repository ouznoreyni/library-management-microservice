package sn.ouznoreyni.bookcatalogservice.dto.genre;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateGenreDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @NotBlank(message = "La description est obligatoire")
    private String description;
}
