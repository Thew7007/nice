package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Patient {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Date birthDate;

    public Patient() {
    }

    // Constructor
    public Patient(int id, String name, String email, String password, String phone, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
    }
    public Patient(ResultSet rs) throws SQLException {
        this(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone"),
                rs.getDate("dateOfBirth")
        );
    }
    // Getters and setters
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

    // toString method to represent object as a string
    @Override
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