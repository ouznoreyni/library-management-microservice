package sn.ouznoreyni.borrowingmanagementservice.dto.borrowing;

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
public class BorrowingListDTO {
    private Long id;
    private Long bookId;
    private String bookName;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private Double lateFee;
    private BorrowingStatus status;
}