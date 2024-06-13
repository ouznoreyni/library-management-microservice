package sn.ouznoreyni.borrowingmanagementservice.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @Column(name = "book_id") // This column will store the id of the book
    private Long bookId; // Store the id of the book

    private LocalDate borrowedDate;

    private LocalDate dueDate;

    private LocalDate returnedDate;

    @Enumerated(EnumType.STRING)
    private BorrowingStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
