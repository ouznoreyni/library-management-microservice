package sn.ouznoreyni.borrowingmanagementservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.ouznoreyni.borrowingmanagementservice.clients.BookRestClient;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingListDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingUpdateDTO;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing;
import sn.ouznoreyni.borrowingmanagementservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.borrowingmanagementservice.mapper.BorrowingMapper;
import sn.ouznoreyni.borrowingmanagementservice.repository.BorrowingRepository;
import sn.ouznoreyni.borrowingmanagementservice.service.BorrowingService;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowingServiceImpl.class);

    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;
    private  final BookRestClient bookRestClient;

    /**
     * Retrieves a borrowing by its ID
     *
     * @param id The ID of the borrowing to retrieve
     * @return The BorrowingDto object corresponding to the borrowing
     */
    @Override
    public BorrowingDTO getBorrowingById(Long id) {
        logger.info("Fetching borrowing with ID: {}", id);
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emprunt non trouvé avec l'ID: " + id));
        return borrowingMapper.toBorrowingDTO(borrowing);
    }

    /**
     * Retrieves all borrowings with pagination
     *
     * @param pageable The pagination parameters
     * @return A Page containing the BorrowingListDto objects corresponding to the borrowings
     */
    @Override
    public Page<BorrowingListDTO> getAllBorrowings(Pageable pageable) {
        logger.info("Fetching all borrowings with pagination");
        return borrowingRepository.findAll(pageable).map(borrowingMapper::toBorrowingListDTO);
    }

    /**
     * Creates a new borrowing
     *
     * @param borrowingCreateDTO The data for the borrowing to create
     * @return The BorrowingDto object corresponding to the newly created borrowing
     */
    @Override
    public BorrowingDTO createBorrowing(BorrowingCreateDTO borrowingCreateDTO) {
        logger.info("Creating new borrowing: {}", borrowingCreateDTO);
        var book = bookRestClient.getBookById(borrowingCreateDTO.getBookId()).orElseThrow(()->{
            logger.error("Book not found");
           return new ResourceNotFoundException("Le livre non trouvé avec l'ID : " + borrowingCreateDTO.getBookId());
        });
        logger.info("getting book from book-catalog microservice: {}", book);


        //get user borrowed
        //if borrowed is already existing, use existing for borrowed
        // if borrowed.userName if not empty
        //save borrowed
        //if borrowed.userName is empty
            //if userName is already existing, use existing userName and info for borrowed
            // if userName is already existing and borrowed is already existing but not the userName,  use username in borrowed


        Borrowing borrowing = borrowingMapper.toBorrowing(borrowingCreateDTO);
        borrowing.setBookName(book.getTitle());
       borrowing = borrowingRepository.save(borrowing);
        return borrowingMapper.toBorrowingDTO(borrowing);
    }

    /**
     * Updates an existing borrowing
     *
     * @param id                 The ID of the borrowing to update
     * @param borrowingUpdateDTO The new data for the borrowing
     * @return The BorrowingDto object corresponding to the updated borrowing
     */
    @Override
    public BorrowingDTO updateBorrowing(Long id, BorrowingUpdateDTO borrowingUpdateDTO) {
        logger.info("Updating borrowing with ID: {}", id);
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emprunt non trouvé avec l'ID: " + id));
        borrowingMapper.updateBorrowingFromDTO(borrowingUpdateDTO, borrowing);
        borrowing = borrowingRepository.save(borrowing);
        return borrowingMapper.toBorrowingDTO(borrowing);
    }

    /**
     * Deletes an existing borrowing
     *
     * @param id The ID of the borrowing to delete
     */
    @Override
    public void deleteBorrowing(Long id) {
        logger.info("Deleting borrowing with ID: {}", id);
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emprunt non trouvé avec l'ID: " + id));
        borrowingRepository.delete(borrowing);
    }
}