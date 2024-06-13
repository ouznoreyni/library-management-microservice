package sn.ouznoreyni.borrowingmanagementservice.dto.borrower;

import java.io.Serializable;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrower}
 */
public record BorrowerListDto(Long id, String name,
                              String phoneNumber) implements Serializable {
}