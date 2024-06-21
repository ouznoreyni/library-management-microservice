package sn.ouznoreyni.borrowingmanagementservice.controlller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerUpdateDTO;
import sn.ouznoreyni.borrowingmanagementservice.service.BorrowerService;

@RestController
@RequestMapping("/api/v1/borrowers")
public class BorrowerController {

    private static final Logger logger = LoggerFactory.getLogger(BorrowerController.class);

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowerDTO> getBorrowerById(@PathVariable Long id) {
        logger.info("GET /api/borrowers/{}", id);
        BorrowerDTO borrowerDTO = borrowerService.getBorrowerById(id);
        logger.info("Fetched borrower with ID: {}", id);
        return ResponseEntity.ok(borrowerDTO);
    }

    @GetMapping
    public ResponseEntity<Page<BorrowerDTO>> getAllBorrowers(Pageable pageable) {
        logger.info("GET /api/borrowers");
        Page<BorrowerDTO> borrowers = borrowerService.getAllBorrowers(pageable);
        logger.info("Fetched all borrowers with pagination");
        return ResponseEntity.ok(borrowers);
    }

    @PostMapping
    public ResponseEntity<BorrowerDTO> createBorrower(@RequestBody BorrowerCreateDTO borrowerCreateDTO) {
        logger.info("POST /api/borrowers with data: {}", borrowerCreateDTO);
        BorrowerDTO createdBorrower = borrowerService.createBorrower(borrowerCreateDTO);
        logger.info("Created new borrower with ID: {}", createdBorrower.getId());
        return new ResponseEntity<>(createdBorrower, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowerDTO> updateBorrower(@PathVariable Long id,
                                                      @RequestBody BorrowerUpdateDTO borrowerUpdateDTO) {
        logger.info("PUT /api/borrowers/{} with data: {}", id, borrowerUpdateDTO);
        BorrowerDTO updatedBorrower = borrowerService.updateBorrower(id, borrowerUpdateDTO);
        logger.info("Updated borrower with ID: {}", id);
        return ResponseEntity.ok(updatedBorrower);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id) {
        logger.info("DELETE /api/borrowers/{}", id);
        borrowerService.deleteBorrower(id);
        logger.info("Deleted borrower with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
