package org.example.dao;

import org.example.model.Schedule;

public class ScheduleDAO extends GenericDAO<Schedule> {

    public ScheduleDAO() {
        super(Schedule.class);
    }

    @Override
    protected String getTableName() {
        return "Schedules";
    }

    @Override
    protected String getIdColumnName() {
        return "id";
    }
}