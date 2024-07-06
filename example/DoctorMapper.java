package org.example.mapper;
import org.example.dto.DoctorDto;
import org.example.model.Doctor;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper( imports = {java.util.UUID.class}, componentModel = "cdi")
public interface DoctorMapper {

    DoctorDto toDocDto(Doctor d);


    DoctorDto toModel(DoctorDto dto);

}
