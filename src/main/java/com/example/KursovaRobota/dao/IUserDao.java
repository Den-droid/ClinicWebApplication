package com.example.KursovaRobota.dao;

import com.example.KursovaRobota.models.Appointment;
import com.example.KursovaRobota.models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserDao {
    User login(String login, String password) throws SQLException;

    User register(User user) throws SQLException;

    ArrayList<Appointment> getUserAppointmentsByDoctorName(int userId, String name) throws SQLException;

    ArrayList<Appointment> getUsersAppointments(int userId) throws SQLException;

    Integer addUserAppointment(int userId, int appointmentId) throws SQLException;

    Integer deleteUserAppointment(int appointmentId, int userId) throws SQLException;
}
