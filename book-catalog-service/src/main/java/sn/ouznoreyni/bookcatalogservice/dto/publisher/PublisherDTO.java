package sn.ouznoreyni.bookcatalogservice.dto.publisher;

import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublisherDTO {
    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
