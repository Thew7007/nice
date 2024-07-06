package org.example.dto;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
public class MedicalReportDto {
    private int id;
    private int patientId;
    private String details;
    private Date reportDate;
    private ArrayList<LinkDto> links = new ArrayList<>();

    public MedicalReportDto(int id, int patientId, String details, Date reportDate) {
        this.id = id;
        this.patientId = patientId;
        this.details = details;
        this.reportDate = reportDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @XmlElementWrapper
    @XmlElement(name = "link")
    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public String toString() {
        return "MedicalReport{" +
                "id=" + id +
                ", patient_Id=" + patientId +
                ", details='" + details + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}