package sn.ouznoreyni.bookcatalogservice.dto.publisher;

import jakarta.validation.constraints.NotBlank;

public record PublisherUpdateDTO(
        String name,
        String address,
        String contactNumber
) {
}
