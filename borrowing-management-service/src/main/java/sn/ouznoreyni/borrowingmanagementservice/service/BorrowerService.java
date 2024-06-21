package sn.ouznoreyni.borrowingmanagementservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerUpdateDTO;

public interface BorrowerService {
    BorrowerDTO getBorrowerById(Long id);

    Page<BorrowerDTO> getAllBorrowers(Pageable pageable);

    BorrowerDTO createBorrower(BorrowerCreateDTO borrowerCreateDTO);

    BorrowerDTO updateBorrower(Long id, BorrowerUpdateDTO borrowerUpdateDTO);

    void deleteBorrower(Long id);
}
