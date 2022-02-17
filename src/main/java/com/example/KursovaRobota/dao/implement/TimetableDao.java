package com.example.KursovaRobota.dao.implement;

import com.example.KursovaRobota.dao.ITimetableDao;
import com.example.KursovaRobota.models.Appointment;
import com.example.KursovaRobota.models.ConnectionParameters;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TimetableDao implements ITimetableDao {
    private final ConnectionParameters conParams = new ConnectionParameters();

    public TimetableDao() {
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

    public Integer addAppointment(Appointment appointment, int doctorId) throws SQLException {
        if (!isAppointmentExisted(appointment, doctorId)) {
            try (Connection connection = DriverManager.getConnection(conParams.getUrl(), 
                    conParams.getUser(), conParams.getPassword())) {
                PreparedStatement statement = connection.prepareStatement("insert into tbltimetable (DoctorId, Date, " +
                        "Time, PlacesAvailable) values (?,?,?,?)");
                statement.setInt(1, doctorId);
                statement.setDate(2, appointment.getDate());
                statement.setTime(3, appointment.getTime());
                statement.setInt(4, appointment.getPlacesAvailable());
                return statement.executeUpdate();
            }
        } else return -1;
    }

    private boolean isAppointmentExisted(Appointment appointment, int doctorsId) throws SQLException{
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("select count(*) from tbltimetable" +
                    " where tbltimetable.DoctorId = ? and tbltimetable.Date = ? and" +
                    " tbltimetable.Time = ?");
            statement.setInt(1, doctorsId);
            statement.setDate(2, appointment.getDate());
            statement.setTime(3, appointment.getTime());
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1) != 0;
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("update tbltimetable " +
                    "set Date = ?, Time = ?, PlacesAvailable = ? where Id = ?");
            statement.setDate(1, appointment.getDate());
            statement.setTime(2, appointment.getTime());
            statement.setInt(3, appointment.getPlacesAvailable());
            statement.setInt(4, appointment.getId());
            statement.executeUpdate();
        }
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("delete from tbltimetable where Id = ?");
            statement.setInt(1, appointmentId);
            statement.executeUpdate();
        }
    }

    public ArrayList<Appointment> getByDoctorsId(int doctorsId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("select tbltimetable.Id, tbldoctors.Fullname, " +
                    "tbldoctors.Specialty, tbltimetable.Date, tbltimetable.Time, tbltimetable.PlacesAvailable\n" +
                    "from tbltimetable\n" +
                    "join tbldoctors on tbldoctors.Id = ? ");
            statement.setInt(1, doctorsId);
            return setAppointmentsList(statement);
        }
    }
}
