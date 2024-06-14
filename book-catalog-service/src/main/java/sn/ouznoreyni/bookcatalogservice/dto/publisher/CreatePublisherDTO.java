package sn.ouznoreyni.bookcatalogservice.dto.publisher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreatePublisherDTO {
    @NotBlank(message = "Le nom de l'éditeur est obligatoire")
    private String name;

    @NotBlank(message = "L'adresse de l'éditeur est obligatoire")
    private String address;

    @NotBlank(message = "Le numéro de contact est obligatoire")
    private String contactNumber;

    @Email(message = "L'adresse de l'éditeur est obligatoire")
    @NotBlank(message = "L'adresse de l'éditeur est obligatoire")
    private String email;

}


