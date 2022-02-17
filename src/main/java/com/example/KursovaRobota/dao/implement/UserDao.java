package com.example.KursovaRobota.dao.implement;

import com.example.KursovaRobota.dao.IUserDao;
import com.example.KursovaRobota.models.Appointment;
import com.example.KursovaRobota.models.ConnectionParameters;
import com.example.KursovaRobota.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.function.Predicate;

public class UserDao implements IUserDao {
    private final ConnectionParameters conParams = new ConnectionParameters();

    public UserDao() {
    }

    @Override
    public User login(String userLogin, String userPassword) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("select * from tblusers where Login = ?" +
                    " and Password = ?");
            statement.setString(1, userLogin);
            statement.setString(2, userPassword);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                int id = set.getInt("Id");
                String fullname = set.getString("Fullname");
                String type = set.getString("Type");
                return new User(id, userLogin, fullname, type);
            } else return null;
        }
    }

    @Override
    public Integer deleteUserAppointment(int appointmentId, int userId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("delete from tbluserappointments " +
                    "where TimeId = ? and UserId = ?");
            statement.setInt(1, appointmentId);
            statement.setInt(2, userId);
            int deletion = statement.executeUpdate();
            changePlacesAvailable(appointmentId, 1);
            return deletion;
        }
    }

    private void changePlacesAvailable(int appointmentId, int change) throws SQLException{
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("update tbltimetable set PlacesAvailable = PlacesAvailable + ?" +
                    " where Id = ?");
            statement.setInt(1, change);
            statement.setInt(2, appointmentId);
            statement.executeUpdate();
        }
    }

    @Override
    public Integer addUserAppointment(int userId, int appointmentId) throws SQLException {
        boolean isAppointed = isUserAppointed(appointmentId, userId);
        boolean isPlacesAvailable = isPlacesAvailable(appointmentId);
        if (!isAppointed && isPlacesAvailable) {
            try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
                PreparedStatement statement = connection.prepareStatement("insert into tbluserappointments (UserId, TimeId) " +
                        " values (?,?)");
                statement.setInt(1, userId);
                statement.setInt(2, appointmentId);
                int insertion = statement.executeUpdate();
                changePlacesAvailable(appointmentId, -1);
                return insertion;
            }
        } else if (!isPlacesAvailable) {
            return -2;
        } else return -1;
    }

    private boolean isPlacesAvailable(int appointmentId) throws SQLException{
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("select count(*) from " +
                    "tbltimetable where Id = ? and PlacesAvailable = 0");
            statement.setInt(1, appointmentId);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1) == 0;
        }
    }

    private boolean isUserAppointed(int appointmentId, int userId) throws SQLException{
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("select count(*) from " +
                    "tbluserappointments where TimeId = ? and UserId = ?");
            statement.setInt(1, appointmentId);
            statement.setInt(2, userId);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1) != 0;
        }
    }

    private boolean isUserExisted(String login) throws SQLException{
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement checkExisted = connection.prepareStatement("select count(*) from tblusers where Login = ?");
            checkExisted.setString(1, login);
            ResultSet set = checkExisted.executeQuery();
            set.next();
            return set.getInt(1) != 0;
        }
    }

    @Override
    public ArrayList<Appointment> getUsersAppointments(int userId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("select tbltimetable.Id, " +
                    "tbldoctors.Fullname, tbldoctors.Specialty, tbltimetable.`Date`,\n" +
                    "    tbltimetable.`Time`, tbltimetable.PlacesAvailable\n" +
                    "from tbluserappointments\n" +
                    "join tblusers on tbluserappointments.UserId = tblusers.Id and tblusers.Id = ?\n" +
                    "join tbltimetable on tbluserappointments.TimeId = tbltimetable.Id \n" +
                    "join tbldoctors on tbldoctors.Id = tbltimetable.DoctorId");
            statement.setInt(1, userId);
            return setAppointmentsList(statement);
        }
    }

    private ArrayList<Appointment> setAppointmentsList(PreparedStatement statement) throws SQLException {
        ResultSet result = statement.executeQuery();
        ArrayList<Appointment> appointments = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("Id");
            String fullname = result.getString("Fullname");
            String specialty = result.getString("Specialty");
            Date date = result.getDate("Date");
            Time time = result.getTime("Time");
            int places = result.getInt("PlacesAvailable");
            Appointment appointment = new Appointment(id, fullname, specialty, date, time, places);
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public ArrayList<Appointment> getUserAppointmentsByDoctorName(int userId, String name) throws SQLException {
        ArrayList<Appointment> appointments = getUsersAppointments(userId);
        Predicate<Appointment> predicate = appointment -> !appointment.getFullname().contains(name);
        appointments.removeIf(predicate);
        return appointments;
    }

    @Override
    public User register(User userReg) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            if (!isUserExisted(userReg.getLogin())) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into tblusers (Login, Password, " +
                        "Fullname, Type) values (?,?,?,?)");
                preparedStatement.setString(1, userReg.getLogin());
                preparedStatement.setString(2, userReg.getPassword());
                preparedStatement.setString(3, userReg.getFullname());
                preparedStatement.setString(4, userReg.getType());
                preparedStatement.executeUpdate();
                return userReg;
            } else {
                return null;
            }
        }
    }
}
