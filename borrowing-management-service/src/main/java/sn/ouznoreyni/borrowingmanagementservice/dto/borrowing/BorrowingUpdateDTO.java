package sn.ouznoreyni.borrowingmanagementservice.dto.borrowing;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import sn.ouznoreyni.borrowingmanagementservice.shared.BorrowingStatus;

import java.time.LocalDate;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing}
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowingUpdateDTO {


        @NotNull(message = "La date d'échéance ne peut pas être vide")
        @FutureOrPresent(message = "La date d'échéance doit être aujourd'hui ou dans le futur")
        private LocalDate dueDate;

        @NotNull(message = "Le montant des frais de retard ne peut pas être vide")
        private Double lateFee;

        @NotNull(message = "Le statut de l'emprunt ne peut pas être vide")
        private BorrowingStatus status;
}