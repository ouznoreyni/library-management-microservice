package sn.ouznoreyni.borrowingmanagementservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerCreateDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerListDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerUpdateDto;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrower;
import sn.ouznoreyni.borrowingmanagementservice.exception.ResourceNotFoundException;
import sn.ouznoreyni.borrowingmanagementservice.mapper.BorrowerMapper;
import sn.ouznoreyni.borrowingmanagementservice.repository.BorrowerRepository;
import sn.ouznoreyni.borrowingmanagementservice.service.BorrowerService;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerServiceImpl.class);

    private final BorrowerRepository borrowerRepository;
    private final BorrowerMapper borrowerMapper;

    /**
     * Retrieves a borrower by its ID
     * @param id The ID of the borrower to retrieve
     * @return The BorrowerDto object corresponding to the borrower
     */
    @Override
    public BorrowerDto getBorrowerById(Long id) {
        logger.info("Fetching borrower with ID: {}", id);
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emprunteur non trouvé avec l'ID : " + id));
        return borrowerMapper.toBorrowerDTO(borrower);
    }

    /**
     * Retrieves all borrowers with pagination
     * @param pageable The pagination parameters
     * @return A Page containing the BorrowerListDto objects corresponding to the borrowers
     */
    @Override
    public Page<BorrowerListDto> getAllBorrowers(Pageable pageable) {
        logger.info("Fetching all borrowers with pagination");
        return borrowerRepository.findAll(pageable).map(borrowerMapper::toBorrowerListDTO);
    }

    /**
     * Creates a new borrower
     * @param borrowerCreateDTO The data for the borrower to create
     * @return The BorrowerDto object corresponding to the newly created borrower
     */
    @Override
    public BorrowerDto createBorrower(BorrowerCreateDto borrowerCreateDTO) {
        logger.info("Creating new borrower: {}", borrowerCreateDTO);
        Borrower borrower = borrowerMapper.toBorrower(borrowerCreateDTO);
        borrower = borrowerRepository.save(borrower);
        return borrowerMapper.toBorrowerDTO(borrower);
    }

    /**
     * Updates an existing borrower
     * @param id The ID of the borrower to update
     * @param borrowerUpdateDTO The new data for the borrower
     * @return The BorrowerDto object corresponding to the updated borrower
     */
    @Override
    public BorrowerDto updateBorrower(Long id, BorrowerUpdateDto borrowerUpdateDTO) {
        logger.info("Updating borrower with ID: {}", id);
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emprunteur non trouvé avec l'ID : " + id));
        borrowerMapper.updateBorrowerFromDTO(borrowerUpdateDTO, borrower);
        borrower = borrowerRepository.save(borrower);
        return borrowerMapper.toBorrowerDTO(borrower);
    }

    /**
     * Deletes an existing borrower
     * @param id The ID of the borrower to delete
     */
    @Override
    public void deleteBorrower(Long id) {
        logger.info("Deleting borrower with ID: {}", id);
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emprunteur non trouvé avec l'ID : " + id));
        borrowerRepository.delete(borrower);
    }
}