package sn.ouznoreyni.borrowingmanagementservice.controlller;
import sn.ouznoreyni.borrowingmanagementservice.service.BorrowingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingListDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingUpdateDTO;

@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowingController {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingController.class);

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingDTO> getBorrowingById(@PathVariable Long id) {
        logger.info("GET /api/borrowings/{}", id);
        BorrowingDTO borrowingDTO = borrowingService.getBorrowingById(id);
        logger.info("Fetched borrowing with ID: {}", id);
        return ResponseEntity.ok(borrowingDTO);
    }

    @GetMapping
    public ResponseEntity<Page<BorrowingListDTO>> getAllBorrowings(Pageable pageable) {
        logger.info("GET /api/borrowings");
        Page<BorrowingListDTO> borrowings = borrowingService.getAllBorrowings(pageable);
        logger.info("Fetched all borrowings with pagination");
        return ResponseEntity.ok(borrowings);
    }

    @PostMapping
    public ResponseEntity<BorrowingDTO> createBorrowing(@RequestBody BorrowingCreateDTO borrowingCreateDTO) {
        logger.info("POST /api/borrowings with data: {}", borrowingCreateDTO);
        BorrowingDTO createdBorrowing = borrowingService.createBorrowing(borrowingCreateDTO);
        logger.info("Created new borrowing with ID: {}", createdBorrowing.getId());
        return new ResponseEntity<>(createdBorrowing, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingDTO> updateBorrowing(@PathVariable Long id,
                                                        @RequestBody BorrowingUpdateDTO borrowingUpdateDTO) {
        logger.info("PUT /api/borrowings/{} with data: {}", id, borrowingUpdateDTO);
        BorrowingDTO updatedBorrowing = borrowingService.updateBorrowing(id, borrowingUpdateDTO);
        logger.info("Updated borrowing with ID: {}", id);
        return ResponseEntity.ok(updatedBorrowing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        logger.info("DELETE /api/borrowings/{}", id);
        borrowingService.deleteBorrowing(id);
        logger.info("Deleted borrowing with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
