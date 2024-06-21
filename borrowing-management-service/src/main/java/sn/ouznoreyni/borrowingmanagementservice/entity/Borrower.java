package sn.ouznoreyni.borrowingmanagementservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private String userName; // user attribute, not the primary identifier

    @OneToMany(mappedBy = "borrower")
    private List<Borrowing> borrowings;

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
