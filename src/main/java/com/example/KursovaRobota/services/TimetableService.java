package com.example.KursovaRobota.services;

import com.example.KursovaRobota.dao.IDaoFactory;
import com.example.KursovaRobota.models.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public class TimetableService implements ITimetableService {
    private final IDaoFactory factory;

    public TimetableService(IDaoFactory factory) {
        this.factory = factory;
    }

    @Override
    public ArrayList<Appointment> getByDoctorsId(int id) throws SQLException {
        return factory.getTimetableDao().getByDoctorsId(id);
    }

    @Override
    public Integer add(Appointment appointment, int doctorId) throws SQLException {
        return factory.getTimetableDao().addAppointment(appointment, doctorId);
    }

    @Override
    public void update(Appointment appointment) throws SQLException {
        factory.getTimetableDao().updateAppointment(appointment);
    }

    @Override
    public void delete(int appointmentId) throws SQLException {
        factory.getTimetableDao().deleteAppointment(appointmentId);
    }
}
