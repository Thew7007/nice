package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.MedicalReportDAO;
import org.example.dto.MedicalReportDto;
import org.example.mapper.MedicalReportMapper;
import org.example.model.MedicalReport;

@Path("/medical-reports")
public class MedicalReportController {

    MedicalReportDAO dao = new MedicalReportDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @Inject
    MedicalReportMapper mapper;


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllMedicalReports(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId) {
        try {
            GenericEntity<List<MedicalReport>> reports = new GenericEntity<List<MedicalReport>>(dao.selectAll()) {};
            GenericEntity<ArrayList<MedicalReportDto>> reportsDto = new GenericEntity<ArrayList<MedicalReportDto>>(new ArrayList<>()) {};
            MedicalReportDto dto;
            for (MedicalReport report: reports.getEntity()) {
                dto = mapper.tomedto(report);
                addLinks(dto);
                reportsDto.getEntity().add(dto);
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(reportsDto)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
                    .ok(reportsDto, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the medical reports.")
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMedicalReport(
            @HeaderParam("Authorization") String authorizationHeader,
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int reportId) {
        try {
            MedicalReport report = dao.selectById(reportId);
            if (report == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Medical report not found.")
                        .build();
            }

            MedicalReportDto dto = mapper.tomedto(report);
            addLinks(dto);
            return Response.ok(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the medical report.")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteMedicalReport(
            @CookieParam("JSESSIONID") Cookie sessionId,
            @PathParam("id") int reportId) {
        try {
            dao.delete(reportId);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while deleting the medical report.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertMedicalReport(MedicalReport report) {
        try {
            dao.insert(report);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(report.getId())).build();
            MedicalReportDto dto = mapper.tomedto(report);
            addLinks(dto);
            return Response.created(uri).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while inserting the medical report.")
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalReport(@PathParam("id") int reportId, MedicalReport report) {
        try {
            report.setId(reportId);
            dao.update(report, reportId);
            MedicalReportDto dto = mapper.tomedto(report);
            addLinks(dto);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while updating the medical report.")
                    .build();
        }
    }

    private void addLinks(MedicalReportDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI reportsUri = uriInfo.getAbsolutePathBuilder()
                .path(MedicalReportController.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(reportsUri.toString(), "medical-reports");
    }
}
