package sn.ouznoreyni.borrowingmanagementservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingListDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingUpdateDTO;

public interface BorrowingService {
    BorrowingDTO getBorrowingById(Long id);

    Page<BorrowingListDTO> getAllBorrowings(Pageable pageable);

    BorrowingDTO createBorrowing(BorrowingCreateDTO borrowingCreateDTO);

    BorrowingDTO updateBorrowing(Long id, BorrowingUpdateDTO borrowingUpdateDTO);

    void deleteBorrowing(Long id);
}
