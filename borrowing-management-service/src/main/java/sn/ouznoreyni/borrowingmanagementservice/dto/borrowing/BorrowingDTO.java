package sn.ouznoreyni.borrowingmanagementservice.dto.borrowing;

import lombok.*;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDTO;
import sn.ouznoreyni.borrowingmanagementservice.shared.BorrowingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing}
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowingDTO {

    private Long id;
    private Long bookId;
    private String bookName;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
    private Double lateFee;
    private BorrowerDTO borrower;
    private BorrowingStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}