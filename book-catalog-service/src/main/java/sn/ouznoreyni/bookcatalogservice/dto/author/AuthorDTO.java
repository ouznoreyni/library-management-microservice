package sn.ouznoreyni.bookcatalogservice.dto.author;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.ToString;
import sn.ouznoreyni.bookcatalogservice.entity.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AuthorDTO(
        Long id,
        String name,
        String biography,
        LocalDate dateOfBirth,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
