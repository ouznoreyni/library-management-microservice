package sn.ouznoreyni.borrowingmanagementservice.dto.borrower;

import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for {@link sn.ouznoreyni.borrowingmanagementservice.entity.Borrower}
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowerDTO {
    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String userName;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}