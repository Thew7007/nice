package org.example.dto;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
public class PatientDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Date birthDate;
    private ArrayList<LinkDto> links = new ArrayList<>();

    public PatientDto(int id, String name, String email, String password, String phone, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}