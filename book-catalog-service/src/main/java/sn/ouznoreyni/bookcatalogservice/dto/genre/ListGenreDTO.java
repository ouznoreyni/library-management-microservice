package sn.ouznoreyni.bookcatalogservice.dto.genre;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListGenreDTO {
    private Long id;
    private String name;
}
