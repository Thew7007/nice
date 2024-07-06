package org.example.dao;

import org.example.model.MedicalReport;

public class MedicalReportDAO extends GenericDAO<MedicalReport> {

    public MedicalReportDAO() {
        super(MedicalReport.class);
    }

    @Override
    protected String getTableName() {
        return "Medical_Reports";
    }

    @Override
    protected String getIdColumnName() {
        return "id";
    }
}