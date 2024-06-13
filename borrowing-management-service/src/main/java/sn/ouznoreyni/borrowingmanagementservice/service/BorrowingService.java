package sn.ouznoreyni.borrowingmanagementservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingCreateDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingListDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingUpdateDto;

public interface BorrowingService {
    BorrowingDto getBorrowingById(Long id);

    Page<BorrowingListDto> getAllBorrowings(Pageable pageable);

    BorrowingDto createBorrowing(BorrowingCreateDto borrowingCreateDTO);

    BorrowingDto updateBorrowing(Long id, BorrowingUpdateDto borrowingUpdateDTO);

    void deleteBorrowing(Long id);
}
