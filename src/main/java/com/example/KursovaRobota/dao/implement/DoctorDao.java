package com.example.KursovaRobota.dao.implement;

import com.example.KursovaRobota.dao.IDoctorDao;
import com.example.KursovaRobota.models.ConnectionParameters;
import com.example.KursovaRobota.models.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DoctorDao implements IDoctorDao {
    private final ConnectionParameters conParams = new ConnectionParameters();

    public DoctorDao() {
    }

    @Override
    public ArrayList<Doctor> getDoctors() throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tbldoctors");
            return setArrayList(result);
        }
    }

    @Override
    public ArrayList<Doctor> getDoctorsByName(String name) throws SQLException {
        try (Connection connection = DriverManager.getConnection(conParams.getUrl(), conParams.getUser(), conParams.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("select * from tbldoctors where Fullname like ?");
            statement.setString(1, "%" + name + "%");
            ResultSet result = statement.executeQuery();
            return setArrayList(result);
        }
    }

    private ArrayList<Doctor> setArrayList(ResultSet result) throws SQLException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("Id");
            String fullname = result.getString("Fullname");
            String specialty = result.getString("Specialty");
            Doctor doctor = new Doctor(id, fullname, specialty);
            doctors.add(doctor);
        }
        return doctors;
    }
}
