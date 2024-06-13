package sn.ouznoreyni.borrowingmanagementservice.dto.borrowing;

import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDto;
import sn.ouznoreyni.borrowingmanagementservice.shared.BorrowingStatus;

import java.io.Serializable;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing}
 */
public record BorrowingListDto(Long id, String borrowerName,
                               BorrowingStatus status) implements Serializable {
}