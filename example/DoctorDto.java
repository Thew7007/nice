package org.example.dto;



import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class DoctorDto {
    private int id;
    private String name;
    private String specialty;
    private String email;
    private String password;
    private String phone;
    private ArrayList<LinkDto> links = new ArrayList<>();

    public DoctorDto(int id, String name, String specialty, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
