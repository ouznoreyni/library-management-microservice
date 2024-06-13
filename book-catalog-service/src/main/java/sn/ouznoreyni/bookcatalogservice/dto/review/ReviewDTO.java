package sn.ouznoreyni.bookcatalogservice.dto.review;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {
    private Long id;
    private String reviewerName;
    private Integer rating;
    private String comment;
}
