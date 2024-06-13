package sn.ouznoreyni.borrowingmanagementservice.dto.borrower;

import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrower}
 */
public record BorrowerCreateDto(
        @NotNull(message = "Veuillez fournir le nom de l'emprunteur.")
        @Size(min = 3, max = 50, message = "Le nom de l'emprunteur doit comporter entre 3 et 50 caractères.")
        @NotEmpty(message = "Le champ nom de l'emprunteur ne peut pas être vide.")
        @NotBlank(message = "Le champ nom de l'emprunteur ne peut pas être vide.")
        String name,

        @NotNull(message = "Veuillez fournir l'email de l'emprunteur.")
        @Size(min = 4, max = 50, message = "L'email de l'emprunteur doit comporter entre 4 et 50 caractères.")
        @Email(message = "Veuillez fournir un email valide pour l'emprunteur.")
        @NotEmpty(message = "Le champ email de l'emprunteur ne peut pas être vide.")
        @NotBlank(message = "Le champ email de l'emprunteur ne peut pas être vide.")
        String email,

        @NotNull(message = "Veuillez fournir le numéro de téléphone de l'emprunteur.")
        @Size(min = 5, max = 20, message = "Le numéro de téléphone de l'emprunteur doit comporter entre 5 et 20 caractères.")
        @NotEmpty(message = "Le champ numéro de téléphone de l'emprunteur ne peut pas être vide.")
        @NotBlank(message = "Le champ numéro de téléphone de l'emprunteur ne peut pas être vide.")
        String phoneNumber,

        @NotNull(message = "Veuillez fournir l'adresse de l'emprunteur.")
        @Size(min = 5, message = "L'adresse de l'emprunteur doit comporter au moins 5 caractères.")
        @NotEmpty(message = "Le champ adresse de l'emprunteur ne peut pas être vide.")
        @NotBlank(message = "Le champ adresse de l'emprunteur ne peut pas être vide.")
        String address
) implements Serializable {
}
