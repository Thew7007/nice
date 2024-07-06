package org.example.dto;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
public class ScheduleDto {
    private int id;
    private int doctorId;
    private Date startTime;
    private Date endTime;
    private boolean isAvailable;
    private ArrayList<LinkDto> links = new ArrayList<>();

    public ScheduleDto(int id, int doctorId, Date startTime, Date endTime, boolean isAvailable) {
        this.id = id;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
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

//    public void setLinks(ArrayList<LinkDto> links) {
//        this.links = links;
//    }

    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", doctor_Id=" + doctorId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isAvailable=" + isAvailable +
                '}';
    }
}