package sn.ouznoreyni.borrowingmanagementservice.dto.borrower;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrower}
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowerUpdateDTO {

        @NotEmpty(message = "Le prénom de l'emprunteur ne peut pas être vide")
        private String firstName;

        @NotEmpty(message = "Le nom de famille de l'emprunteur ne peut pas être vide")
        private String lastName;

        @Email(message = "L'adresse e-mail de l'emprunteur doit être valide")
        private String email;

        @Pattern(regexp = "\\d{20}", message = "Le numéro de téléphone de l'emprunteur doit être composé de 20 chiffres")
        private String phoneNumber;

        @NotEmpty(message = "L'adresse de l'emprunteur ne peut pas être vide")
        private String address;
}