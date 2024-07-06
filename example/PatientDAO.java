package org.example.dao;

import org.example.model.Patient;

public class PatientDAO extends GenericDAO<Patient> {

    public PatientDAO() {
        super(Patient.class);
    }

    @Override
    protected String getTableName() {
        return "Patients";
    }

    @Override
    protected String getIdColumnName() {
        return "id";
    }
}