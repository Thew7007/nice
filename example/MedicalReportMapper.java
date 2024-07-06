package org.example.mapper;
import org.example.dto.MedicalReportDto;
import org.example.model.MedicalReport;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper( imports = {java.util.UUID.class}, componentModel = "cdi")
public interface MedicalReportMapper {

    MedicalReportDto tomedto(MedicalReport m);


    MedicalReportDto toModel(MedicalReportDto mto);

}
