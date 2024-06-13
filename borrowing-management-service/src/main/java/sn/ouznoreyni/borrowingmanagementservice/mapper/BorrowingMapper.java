package sn.ouznoreyni.borrowingmanagementservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingCreateDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingListDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrowing.BorrowingUpdateDto;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing;

@Component
public class BorrowingMapper {

    private final ModelMapper modelMapper;

    public BorrowingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BorrowingDto toBorrowingDTO(Borrowing borrowing) {
        return modelMapper.map(borrowing, BorrowingDto.class);
    }

    public BorrowingListDto toBorrowingListDTO(Borrowing borrowing) {
        return modelMapper.map(borrowing, BorrowingListDto.class);
    }

    public Borrowing toBorrowing(BorrowingCreateDto borrowingCreateDTO) {
        return modelMapper.map(borrowingCreateDTO, Borrowing.class);
    }

    public void updateBorrowingFromDTO(BorrowingUpdateDto borrowingUpdateDTO, Borrowing borrowing) {
        modelMapper.map(borrowingUpdateDTO, borrowing);
    }
}