package sn.ouznoreyni.bookcatalogservice.dto.book;

import sn.ouznoreyni.bookcatalogservice.shared.BookStatus;

public record BookListDTO(
        Long id,
        String title,
        String authorName,
        String genreName,
        BookStatus status
) {
}
