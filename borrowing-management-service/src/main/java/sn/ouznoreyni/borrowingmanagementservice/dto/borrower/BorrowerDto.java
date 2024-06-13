package sn.ouznoreyni.borrowingmanagementservice.dto.borrower;

import jakarta.validation.constraints.Email;

import java.io.Serializable;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrower}
 */
public record BorrowerDto(Long id, String name, String email,
                          String phoneNumber,
                          String address) implements Serializable {
}