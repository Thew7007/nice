package org.example.dao;

import org.example.model.Consultation;

public class ConsultationDAO extends GenericDAO<Consultation> {

    public ConsultationDAO() {
        super(Consultation.class);
    }

    @Override
    protected String getTableName() {
        return "Consultations";
    }

    @Override
    protected String getIdColumnName() {
        return "id";
    }
}