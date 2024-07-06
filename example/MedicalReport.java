package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MedicalReport {
    private int id;
    private int patientId;
    private String details;
    private Date reportDate;

    public MedicalReport() {
    }

    // Constructor
    public MedicalReport(int id, int patientId, String details, Date reportDate) {
        this.id = id;
        this.patientId = patientId;
        this.details = details;
        this.reportDate = reportDate;
    }
    public MedicalReport(ResultSet rs) throws SQLException {
        this(
                rs.getInt("id"),
                rs.getInt("patient_id"),
                rs.getString("details"),
                rs.getTimestamp("reportDate")
        );
    }

    // Getters and setters
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

    // toString method to represent object as a string
    @Override
    public String toString() {
        return "MedicalReport{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", details='" + details + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}