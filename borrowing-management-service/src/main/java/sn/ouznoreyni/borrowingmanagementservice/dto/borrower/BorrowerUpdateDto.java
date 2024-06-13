package sn.ouznoreyni.borrowingmanagementservice.dto.borrower;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrower}
 */
public record BorrowerUpdateDto(
        @NotNull(message = "Veuillez fournir l' id de l'emprunteur.")
        Long id, String name, @Email String email,
                                String phoneNumber,
                                String address) implements Serializable {
}