package sn.ouznoreyni.borrowingmanagementservice.dto.borrowing;

import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDto;
import sn.ouznoreyni.borrowingmanagementservice.shared.BorrowingStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing}
 */
public record BorrowingDto(Long id, BorrowerDto borrower, Long bookId,
                           LocalDate borrowedDate, LocalDate dueDate,
                           LocalDate returnedDate, BorrowingStatus status,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) implements Serializable {
}