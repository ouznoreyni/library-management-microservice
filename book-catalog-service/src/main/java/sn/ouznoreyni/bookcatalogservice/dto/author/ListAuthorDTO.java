package sn.ouznoreyni.bookcatalogservice.dto.author;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListAuthorDTO {
    private Long id;
    private String name;

}
