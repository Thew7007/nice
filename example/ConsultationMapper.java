package org.example.mapper;
import org.example.dto.ConsultationDto;
import org.example.model.Consultation;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper( imports = {java.util.UUID.class}, componentModel = "cdi")
public interface ConsultationMapper {

    ConsultationDto toconsdto(Consultation c);


    ConsultationDto toModel(ConsultationDto cto);

}
