package sn.ouznoreyni.borrowingmanagementservice.dto.borrowing;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import sn.ouznoreyni.borrowingmanagementservice.shared.BorrowingStatus;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing}
 */
public record BorrowingCreateDto(

        @Positive(message = "L'identifiant de l'emprunteur doit être un nombre positif.")
        Long borrowerId,

        @Size(max = 50, message = "Le nom de l'emprunteur doit comporter au maximum 50 caractères.")
        String borrowerName,

        @NotNull(message = "L'identifiant du livre est obligatoire.")
        @Positive(message = "L'identifiant du livre doit être un nombre positif.")
        Long bookId,

        @NotNull(message = "La date d'emprunt est obligatoire.")
        LocalDate borrowedDate,

        @NotNull(message = "La date de retour prévue est obligatoire.")
        LocalDate dueDate,

        @NotNull(message = "Le statut de l'emprunt est obligatoire.")
        BorrowingStatus status
) implements Serializable {
}