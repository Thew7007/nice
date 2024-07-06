package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private int id;
    private String name;
    private String specialty;
    private String email;
    private String password;
    private String phone;

    public Doctor() {
    }

    // Constructor
    public Doctor(int id, String name, String specialty, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Doctor(ResultSet rs) throws SQLException {
        this(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("specialty"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone")
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

    // toString method to represent object as a string
    @Override
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