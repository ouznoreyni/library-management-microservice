package sn.ouznoreyni.borrowingmanagementservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingListDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingUpdateDTO;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing;

@Component
public class BorrowingMapper {

    private final ModelMapper modelMapper;

    public BorrowingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BorrowingDTO toBorrowingDTO(Borrowing borrowing) {
        return modelMapper.map(borrowing, BorrowingDTO.class);
    }

    public BorrowingListDTO toBorrowingListDTO(Borrowing borrowing) {
        return modelMapper.map(borrowing, BorrowingListDTO.class);
    }

    public Borrowing toBorrowing(BorrowingCreateDTO borrowingCreateDTO) {
        return modelMapper.map(borrowingCreateDTO, Borrowing.class);
    }

    public void updateBorrowingFromDTO(BorrowingUpdateDTO borrowingUpdateDTO, Borrowing borrowing) {
        modelMapper.map(borrowingUpdateDTO, borrowing);
    }
}