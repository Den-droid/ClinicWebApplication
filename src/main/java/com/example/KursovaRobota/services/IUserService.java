package com.example.KursovaRobota.services;

import com.example.KursovaRobota.models.Appointment;
import com.example.KursovaRobota.models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserService {
    User login(User user) throws SQLException;

    User register(User user) throws SQLException;

    ArrayList<Appointment> getAppointments(int userId) throws SQLException;

    ArrayList<Appointment> getAppointmentsByDoctorName(int userId, String name) throws SQLException;

    Integer addAppointment(int userId, int appointmentId) throws SQLException;
    Integer deleteAppointment(int appointmentId, int userId) throws SQLException;
}
