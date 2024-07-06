package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Consultation {
    private int id;
    private int doctorId;
    private int patientId;
//    private Date requestTime;
//    private Date consultationTime;
    private String status;
    private String diagnosis;

    private String rating;

    public Consultation() {
    }

    // Constructor
    public Consultation(int id, int doctorId, int patientId, String status, String diagnosis
                        ,String rating) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
//        this.requestTime = requestTime;
//        this.consultationTime = consultationTime;
        this.status = status;
        this.diagnosis = diagnosis;

        this.rating = rating;
    }
    public Consultation(ResultSet rs) throws SQLException {
        this(
                rs.getInt("id"),
                rs.getInt("doctorId"),
                rs.getInt("patientId"),
//                rs.getTimestamp("requestTime"),
//                rs.getTimestamp("consultationTime"),
                rs.getString("status"),
                rs.getString("diagnosis"),

                rs.getString("rating")
        );
    }

    // Getters and setters
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


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // toString method to represent object as a string
    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
//                ", requestTime=" + requestTime +
//                ", consultationTime=" + consultationTime +
                ", status='" + status + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", rating='" + rating +'\''+
                '}';
    }
}