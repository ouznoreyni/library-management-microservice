package sn.ouznoreyni.bookcatalogservice.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateReviewDTO {
    @NotNull(message = "L'identifiant du livre est obligatoire")
    private Long bookId;

    @NotBlank(message = "Le nom du critique est obligatoire")
    private String reviewerName;

    @NotNull(message = "La note est obligatoire")
    @Min(value = 1, message = "La note minimale est 1")
    @Max(value = 5, message = "La note maximale est 5")
    private Integer rating;

    @NotBlank(message = "Le commentaire est obligatoire")
    private String comment;
}