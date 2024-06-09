package sn.ouznoreyni.bookcatalogservice.dto.publisher;

import java.time.LocalDateTime;

public record PublisherDTO(
         Long id,

         String name,
         String address,
         String contactNumber,

         LocalDateTime createdAt,
         LocalDateTime updatedAt
) {
}
