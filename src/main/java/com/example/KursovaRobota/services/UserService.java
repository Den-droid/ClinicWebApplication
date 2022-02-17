package com.example.KursovaRobota.services;

import com.example.KursovaRobota.dao.IDaoFactory;
import com.example.KursovaRobota.models.Appointment;
import com.example.KursovaRobota.models.User;
import com.example.KursovaRobota.utils.CryptWithMD5;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService implements IUserService{
    private final IDaoFactory factory;

    public UserService(IDaoFactory factory) {
        this.factory = factory;
    }

    @Override
    public User login(User user) throws SQLException {
        String hashPassword = CryptWithMD5.cryptWithMD5(user.getPassword());
        return factory.getUserDao().login(user.getLogin(), hashPassword);
    }

    @Override
    public User register(User user) throws SQLException {
        String hashPassword = CryptWithMD5.cryptWithMD5(user.getPassword());
        user.setPassword(hashPassword);
        return factory.getUserDao().register(user);
    }

    @Override
    public ArrayList<Appointment> getAppointments(int userId) throws SQLException {
        return factory.getUserDao().getUsersAppointments(userId);
    }

    @Override
    public ArrayList<Appointment> getAppointmentsByDoctorName(int userId, String name) throws SQLException {
        return factory.getUserDao().getUserAppointmentsByDoctorName(userId, name);
    }

    @Override
    public Integer addAppointment(int userId, int appointmentId) throws SQLException {
        return factory.getUserDao().addUserAppointment(userId, appointmentId);
    }

    @Override
    public Integer deleteAppointment(int appointmentId, int userId) throws SQLException {
        return factory.getUserDao().deleteUserAppointment(appointmentId, userId);
    }
}
