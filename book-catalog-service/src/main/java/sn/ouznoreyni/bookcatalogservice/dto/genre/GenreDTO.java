package sn.ouznoreyni.bookcatalogservice.dto.genre;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import sn.ouznoreyni.bookcatalogservice.entity.Book;

import java.time.LocalDateTime;
import java.util.List;

public record GenreDTO(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}