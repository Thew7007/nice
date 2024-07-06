package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.ConsultationDAO;
import org.example.dto.ConsultationDto;
import org.example.mapper.ConsultationMapper;
import org.example.model.Consultation;

@Path("/consultations")
public class ConsultationController {

    ConsultationDAO dao = new ConsultationDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @Inject
    ConsultationMapper mapper;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllConsultations(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId) {
        try {
            GenericEntity<List<Consultation>> consultations = new GenericEntity<List<Consultation>>(dao.selectAll()) {};
            GenericEntity<ArrayList<ConsultationDto>> consultationsDto = new GenericEntity<ArrayList<ConsultationDto>>(new ArrayList<>()) {};
            ConsultationDto dto;
            for (Consultation consultation: consultations.getEntity()) {
                dto = mapper.toconsdto(consultation);
                addLinks(dto);
                consultationsDto.getEntity().add(dto);
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(consultationsDto)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
                    .ok(consultationsDto, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the consultations.")
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getConsultation(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int consultationId) {
        try {
            Consultation consultation = dao.selectById(consultationId);
            if (consultation == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Consultation not found.")
                        .build();
            }

            ConsultationDto dto = mapper.toconsdto(consultation);
            addLinks(dto);
            return Response.ok(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the consultation.")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteConsultation(
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int consultationId) {
        try {
            dao.delete(consultationId);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while deleting the consultation.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertConsultation(Consultation consultation) {
        try {
            dao.insert(consultation);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(consultation.getId())).build();
            ConsultationDto dto = mapper.toconsdto(consultation);
            addLinks(dto);
            return Response.created(uri).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while inserting the consultation.")
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateConsultation(@PathParam("id") int consultationId, Consultation consultation) {
        try {
            consultation.setId(consultationId);
            dao.update(consultation, consultationId);
            ConsultationDto dto = mapper.toconsdto(consultation);
            addLinks(dto);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while updating the consultation.")
                    .build();
        }
    }

    private void addLinks(ConsultationDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI consultationsUri = uriInfo.getAbsolutePathBuilder()
                .path(ConsultationController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(consultationsUri.toString(), "consultations");
    }
}
