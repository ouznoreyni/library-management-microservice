package sn.ouznoreyni.bookcatalogservice.shared;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import sn.ouznoreyni.bookcatalogservice.entity.Book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Specifications {
    public static Specification<Book> buildBookSpecification(
            String title, String isbn, String authorName, String genreName, String publisherName,
            LocalDateTime publishedAfter, LocalDateTime publishedBefore) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Add predicates based on the provided parameters

            if (!StringUtils.isEmpty(title)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(isbn)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("isbn")), "%" + isbn.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(authorName)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("author").get("name")), "%" + authorName.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(genreName)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("genre").get("name")), "%" + genreName.toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(publisherName)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("publisher").get("name")), "%" + publisherName.toLowerCase() + "%"));
            }

            if (publishedAfter != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), publishedAfter));
            }

            if (publishedBefore != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), publishedBefore));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
