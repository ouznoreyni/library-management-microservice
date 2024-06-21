package sn.ouznoreyni.borrowingmanagementservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerCreateDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerDTO;
import sn.ouznoreyni.borrowingmanagementservice.dto.borrower.BorrowerUpdateDTO;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrower;

@Component
public class BorrowerMapper {
    private final ModelMapper modelMapper;

    public BorrowerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BorrowerDTO toBorrowerDTO(Borrower borrower) {
        return modelMapper.map(borrower, BorrowerDTO.class);
    }


    public Borrower toBorrowerEntity(BorrowerCreateDTO borrowerCreateDTO) {
        return modelMapper.map(borrowerCreateDTO, Borrower.class);
    }

    public void updateBorrowerFromDTO(BorrowerUpdateDTO borrowerUpdateDTO, Borrower borrower) {
        modelMapper.map(borrowerUpdateDTO, borrower);
    }
}
