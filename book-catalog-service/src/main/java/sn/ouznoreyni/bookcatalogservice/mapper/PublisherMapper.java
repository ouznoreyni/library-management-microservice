package sn.ouznoreyni.bookcatalogservice.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.CreatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.ListPublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.PublisherDTO;
import sn.ouznoreyni.bookcatalogservice.dto.publisher.UpdatePublisherDTO;
import sn.ouznoreyni.bookcatalogservice.entity.Publisher;


@Component
@AllArgsConstructor
public class PublisherMapper {
    private final ModelMapper modelMapper;

    public PublisherDTO toPublisherDTO(Publisher publisher) {
        return modelMapper.map(publisher, PublisherDTO.class);
    }


    public Publisher toPublisherEntity(PublisherDTO publisherDTO) {
        return modelMapper.map(publisherDTO, Publisher.class);
    }

    public Publisher toPublisherEntity(CreatePublisherDTO createPublisherDTO) {
        return modelMapper.map(createPublisherDTO, Publisher.class);
    }

    public Publisher toPublisherEntity(UpdatePublisherDTO updatePublisherDTO) {
        return modelMapper.map(updatePublisherDTO, Publisher.class);
    }

    public ListPublisherDTO toPublisherListDTO(Publisher publisher) {
        return ListPublisherDTO.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }


}
