package org.example.dao;
import org.example.model.Doctor;

public class DoctorDAO extends GenericDAO<Doctor> {

    public DoctorDAO() {
        super(Doctor.class);
    }

    @Override
    protected String getTableName() {
        return "Doctors";
    }

    @Override
    protected String getIdColumnName() {
        return "id";
    }
}