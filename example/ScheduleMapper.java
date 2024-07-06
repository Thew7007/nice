package org.example.mapper;
import org.example.dto.ScheduleDto;
import org.example.model.Schedule;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper( imports = {java.util.UUID.class}, componentModel = "cdi")
public interface ScheduleMapper {

    ScheduleDto toScheduleDto(Schedule s);


    ScheduleDto toModel(ScheduleDto sto);

}
