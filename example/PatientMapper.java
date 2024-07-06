package org.example.mapper;
import org.example.dto.PatientDto;
import org.example.model.Patient;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper( imports = {java.util.UUID.class}, componentModel = "cdi")
public interface PatientMapper {

    PatientDto topatientdto(Patient p);


    PatientDto toModel(PatientDto pto);

}
