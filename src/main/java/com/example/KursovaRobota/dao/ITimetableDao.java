package com.example.KursovaRobota.dao;

import com.example.KursovaRobota.models.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ITimetableDao {
    ArrayList<Appointment> getByDoctorsId(int id) throws SQLException;

    Integer addAppointment(Appointment appointment, int doctorId) throws SQLException;

    void updateAppointment(Appointment appointment) throws SQLException;

    void deleteAppointment(int appointmentId) throws SQLException;
}
