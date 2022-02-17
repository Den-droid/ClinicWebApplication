package com.example.KursovaRobota.services;

import com.example.KursovaRobota.models.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ITimetableService {
    ArrayList<Appointment> getByDoctorsId(int id) throws SQLException;

    Integer add(Appointment appointment, int doctorId) throws SQLException;

    void update(Appointment appointment) throws SQLException;

    void delete(int appointmentId) throws SQLException;
}
