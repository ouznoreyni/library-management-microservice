package sn.ouznoreyni.borrowingmanagementservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerCreateDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerListDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerUpdateDto;

public interface BorrowerService {
    BorrowerDto getBorrowerById(Long id);

    Page<BorrowerListDto> getAllBorrowers(Pageable pageable);

    BorrowerDto createBorrower(BorrowerCreateDto borrowerCreateDTO);

    BorrowerDto updateBorrower(Long id, BorrowerUpdateDto borrowerUpdateDTO);

    void deleteBorrower(Long id);
}
