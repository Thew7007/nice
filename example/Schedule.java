package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Schedule {
    private int id;
    private int doctorId;
    private Date startTime;
    private Date endTime;
    private boolean isAvailable;

    public Schedule() {
    }

    // Constructor
    public Schedule(int id, int doctorId, Date startTime, Date endTime, boolean isAvailable) {
        this.id = id;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    // Constructor with ResultSet
    public Schedule(ResultSet rs) throws SQLException {
        this(
                rs.getInt("id"),
                rs.getInt("doctor_id"),
                rs.getTimestamp("startTime"),
                rs.getTimestamp("endTime"),
                rs.getBoolean("isAvailable")
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // toString method to represent object as a string
    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isAvailable=" + isAvailable +
                '}';
    }
}