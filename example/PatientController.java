package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.PatientDAO;
import org.example.dto.PatientDto;
import org.example.mapper.PatientMapper;
import org.example.model.Patient;

@Path("/patients")
public class PatientController {

    PatientDAO dao = new PatientDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @Inject
    PatientMapper mapper;


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllPatients(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId) {
        try {
            GenericEntity<List<Patient>> patients = new GenericEntity<List<Patient>>(dao.selectAll()) {};
            GenericEntity<ArrayList<PatientDto>> patientsDto = new GenericEntity<ArrayList<PatientDto>>(new ArrayList<>()) {};
            PatientDto dto;
            for (Patient patient: patients.getEntity()) {
                dto = mapper.topatientdto(patient);
                addLinks(dto);
                patientsDto.getEntity().add(dto);
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(patientsDto)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
                    .ok(patientsDto, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the patients.")
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getPatient(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int patientId) {
        try {
            Patient patient = dao.selectById(patientId);
            if (patient == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Patient not found.")
                        .build();
            }

            PatientDto dto = mapper.topatientdto(patient);
            addLinks(dto);
            return Response.ok(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the patient.")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePatient(
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int patientId) {
        try {
            dao.delete(patientId);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while deleting the patient.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertPatient(Patient patient) {
        try {
            dao.insert(patient);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(patient.getId())).build();
            PatientDto dto = mapper.topatientdto(patient);
            addLinks(dto);
            return Response.created(uri).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while inserting the patient.")
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") int patientId, Patient patient) {
        try {
            patient.setId(patientId);
            dao.update(patient, patientId);
            PatientDto dto = mapper.topatientdto(patient);
            addLinks(dto);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while updating the patient.")
                    .build();
        }
    }

    private void addLinks(PatientDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI patientsUri = uriInfo.getAbsolutePathBuilder()
                .path(PatientController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(patientsUri.toString(), "patients");
    }
}