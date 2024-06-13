package sn.ouznoreyni.borrowingmanagementservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerCreateDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerListDto;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerUpdateDto;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrower;

@Component
public class BorrowerMapper {
    private final ModelMapper modelMapper;

    public BorrowerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BorrowerDto toBorrowerDTO(Borrower borrower) {
        return modelMapper.map(borrower, BorrowerDto.class);
    }

    public BorrowerListDto toBorrowerListDTO(Borrower borrower) {
        return modelMapper.map(borrower, BorrowerListDto.class);
    }

    public Borrower toBorrower(BorrowerCreateDto borrowerCreateDTO) {
        return modelMapper.map(borrowerCreateDTO, Borrower.class);
    }

    public void updateBorrowerFromDTO(BorrowerUpdateDto borrowerUpdateDTO, Borrower borrower) {
        modelMapper.map(borrowerUpdateDTO, borrower);
    }
}
