package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.ScheduleDAO;
import org.example.dto.ScheduleDto;
import org.example.mapper.ScheduleMapper;
import org.example.model.Schedule;

@Path("/schedules")
public class ScheduleController {

    ScheduleDAO dao = new ScheduleDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @Inject
    ScheduleMapper mapper;


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllSchedules(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId) {
        try {
            GenericEntity<List<Schedule>> schedules = new GenericEntity<List<Schedule>>(dao.selectAll()) {};
            GenericEntity<ArrayList<ScheduleDto>> schedulesDto = new GenericEntity<ArrayList<ScheduleDto>>(new ArrayList<>()) {};
            ScheduleDto dto;
            for (Schedule schedule: schedules.getEntity()) {
                dto = mapper.toScheduleDto(schedule);
                addLinks(dto);
                schedulesDto.getEntity().add(dto);
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(schedulesDto)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
                    .ok(schedulesDto, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the schedules.")
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSchedule(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int scheduleId) {
        try {
            Schedule schedule = dao.selectById(scheduleId);
            if (schedule == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Schedule not found.")
                        .build();
            }

            ScheduleDto dto = mapper.toScheduleDto(schedule);
            addLinks(dto);
            return Response.ok(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the schedule.")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteSchedule(
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int scheduleId) {
        try {
            dao.delete(scheduleId);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while deleting the schedule.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertSchedule(Schedule schedule) {
        try {
            dao.insert(schedule);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(schedule.getId())).build();
            ScheduleDto dto = mapper.toScheduleDto(schedule);
            addLinks(dto);
            return Response.created(uri).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while inserting the schedule.")
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSchedule(@PathParam("id") int scheduleId, Schedule schedule) {
        try {
            schedule.setId(scheduleId);
            dao.update(schedule, scheduleId);
            ScheduleDto dto = mapper.toScheduleDto(schedule);
            addLinks(dto);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while updating the schedule.")
                    .build();
        }
    }

    private void addLinks(ScheduleDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI schedulesUri = uriInfo.getAbsolutePathBuilder()
                .path(ScheduleController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(schedulesUri.toString(), "schedules");
    }
}
