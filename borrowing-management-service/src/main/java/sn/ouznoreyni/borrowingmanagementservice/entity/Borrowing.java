package sn.ouznoreyni.borrowingmanagementservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import sn.ouznoreyni.borrowingmanagementservice.shared.BorrowingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @NotNull(message = "L'identifiant du livre ne peut pas être vide")
    private Long bookId;

    @NotNull(message = "Le nom du livre ne peut pas être vide")
    @Size(min = 1, max = 255, message = "Le nom du livre doit avoir entre 1 et 255 caractères")
    private String bookName; // Adding book name as a field


    @NotNull(message = "La date d'emprunt ne peut pas être vide")
    @FutureOrPresent(message = "La date d'emprunt doit être aujourd'hui ou dans le futur")
    private LocalDate borrowedDate;

    @NotNull(message = "La date d'échéance ne peut pas être vide")
    @FutureOrPresent(message = "La date d'échéance doit être aujourd'hui ou dans le futur")
    private LocalDate dueDate;

    private LocalDate returnedDate;

    @NotNull(message = "Le montant des frais de retard ne peut pas être vide")
    private Double lateFee;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le statut de l'emprunt ne peut pas être vide")
    private BorrowingStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
