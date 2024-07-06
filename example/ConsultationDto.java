package org.example.dto;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
public class ConsultationDto {
    private int id;
    private int doctorId;
    private int patientId;
//    private Date requestTime;
//    private Date consultationTime;
    private String status;
    private String diagnosis;

    private String rating;

    private ArrayList<LinkDto> links = new ArrayList<>();

    public ConsultationDto(int id, int doctorId, int patientId, String status, String diagnosis,String rating) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
//        this.requestTime = requestTime;
//        this.consultationTime = consultationTime;
        this.status = status;
        this.diagnosis = diagnosis;

        this.rating = rating;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

//    public Date getRequestTime() {
//        return requestTime;
//    }
//
//    public void setRequestTime(Date requestTime) {
//        this.requestTime = requestTime;
//    }
//
//    public Date getConsultationTime() {
//        return consultationTime;
//    }
//
//    public void setConsultationTime(Date consultationTime) {
//        this.consultationTime = consultationTime;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @XmlElementWrapper
    @XmlElement(name = "link")
    public ArrayList<LinkDto> getLinks() {
        return links;
    }

//    public void setLinks(ArrayList<LinkDto> links) {
//        this.links = links;
//    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", doctor_Id=" + doctorId +
                ", patient_Id=" + patientId +
//                ", requestTime=" + requestTime +
//                ", consultationTime=" + consultationTime +
                ", status='" + status + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", rating='" + rating +'\''+
                '}';
    }
}