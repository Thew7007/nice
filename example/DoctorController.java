package org.example.Controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.DoctorDAO;
import org.example.dto.DoctorDto;
import org.example.mapper.DoctorMapper;
import org.example.model.Doctor;

@Path("/doctors")
public class DoctorController {

    DoctorDAO dao = new DoctorDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @Inject
    DoctorMapper mapper;


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllDoctors(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId) {
        try {
            GenericEntity<List<Doctor>> doctors = new GenericEntity<List<Doctor>>(dao.selectAll()) {};
            GenericEntity<List<DoctorDto>> doctorsDto = new GenericEntity<List<DoctorDto>>(new ArrayList<>()) {};
            DoctorDto dto;
            for (Doctor doctor:
                 doctors.getEntity()) {
                dto = mapper.toDocDto(doctor);
                addLinks(dto);
                doctorsDto.getEntity().add(dto);
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(doctorsDto)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
                    .ok(doctorsDto, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the doctors.")
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDoctor(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int doctorId) {
        try {
            Doctor doctor = dao.selectById(doctorId);
            if (doctor == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Job not found.")
                        .build();
            }



            //jobDto jobto = JobMapper.INSTANCE.toJObDto(job);
            DoctorDto dto = mapper.toDocDto(doctor);
            addLinks(dto);
            return Response.ok(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the job.")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteDoctor(
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int DoctorId) {
        try {
            dao.delete(DoctorId);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while deleting the job.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertDoctor(Doctor tDoctor) {
        try {
            dao.insert(tDoctor);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(tDoctor.getId())).build();
            DoctorDto dto = mapper.toDocDto(tDoctor);
            addLinks(dto);
            return Response.created(uri).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while inserting the job.")
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") int DoctorId, Doctor tdoctor) {
        try {
            tdoctor.setId(DoctorId);
            dao.update(tdoctor,DoctorId);
            DoctorDto dto = mapper.toDocDto(tdoctor);
            addLinks(dto);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while updating the job.")
                    .build();
        }
    }

    private void addLinks(DoctorDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI docsUri = uriInfo.getAbsolutePathBuilder()
                .path(DoctorController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(docsUri.toString(), "doctors");
    }


}
