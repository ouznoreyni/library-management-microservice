package sn.ouznoreyni.bookcatalogservice.shared;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CatalogServiceUtils {
    private static final Logger logger = LoggerFactory.getLogger(CatalogServiceUtils.class);

    /**
     * Create a PageRequest object with sorting and pagination parameters.
     * Default sort by createdAt descending if sortBy is not specified.
     *
     * @param page         Page number (default: 0)
     * @param size         Number of items per page (default: 10, max: 50)
     * @param sortBy       Field to sort by (optional: title, isbn, createdAt, updatedAt, author.name, genre.name, publisher.name)
     * @param sortDesc     Sort direction (optional: asc, desc)
     * @param defaultSort  Default sort order if sortBy is not specified
     * @param validSortFields Array of valid fields for sorting
     * @return PageRequest object configured with sorting and pagination parameters
     */
    public static PageRequest createPageRequest(int page, int size, String sortBy, String sortDesc, Sort.Order defaultSort, String[] validSortFields) {
        // Validate and sanitize page and size parameters
        page = Math.max(page, 0);  // Page should not be negative
        size = Math.min(Math.max(size, 1), 50);  // Size should be between 1 and 50

        // Validate sortBy parameter to prevent sorting by invalid fields
        Sort sort = Sort.by(sortDesc.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                isValidSortField(sortBy, validSortFields) ? sortBy : defaultSort.getProperty());

        if (!isValidSortField(sortBy, validSortFields)) {
            logger.warn("Invalid sortBy field: {}. Defaulting to sortBy={}", sortBy, defaultSort.getProperty());
            sort = Sort.by(defaultSort);
        }

        return PageRequest.of(page, size, sort);
    }
    public static Sort buildSort(String sortBy, String sortDesc) {
        Sort.Order defaultSort = Sort.Order.desc("createdAt"); // Default sort by createdAt descending

        if (!StringUtils.hasText(sortBy)) {
            return Sort.by(defaultSort);
        }

        Sort.Direction direction = sortDesc != null && sortDesc.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        if (!isValidSortField(sortBy)) {
            // If sortBy is invalid, log a warning and default to defaultSort
            System.out.println("Invalid sortBy field: " + sortBy + ". Defaulting to sortBy=" + defaultSort.getProperty());
            return Sort.by(defaultSort);
        }

        return Sort.by(direction, sortBy);
    }

    private static boolean isValidSortField(String sortBy) {
        // Add logic to validate sortBy field (if needed)
        // Example: return sortBy.equals("title") || sortBy.equals("createdAt") || ...
        return true; // For simplicity, assume all fields are valid
    }

    /**
     * Parse date-time string into LocalDateTime object.
     *
     * @param dateTimeStr Date-time string
     * @return LocalDateTime object parsed from the string, or null if parsing fails
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isBlank()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr);
        } catch (DateTimeParseException e) {
            logger.error("Error parsing date-time string: {}", dateTimeStr, e);
            return null;
        }
    }

    /**
     * Helper method to validate if sortBy parameter is a valid sorting field.
     *
     * @param sortBy       Field to sort by
     * @param validSortFields Array of valid fields for sorting
     * @return true if valid, false otherwise
     */
    private static boolean isValidSortField(String sortBy, String[] validSortFields) {
        for (String validField : validSortFields) {
            if (validField.equals(sortBy)) {
                return true;
            }
        }
        return false;
    }
}
