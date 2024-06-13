package sn.ouznoreyni.bookcatalogservice.dto.publisher;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatePublisherDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String name;
    @NotBlank(message = "L'adresse est obligatoire")
    private String address;
    @NotBlank(message = "Le numéro de contact est obligatoire")
    private String contactNumber;
}
