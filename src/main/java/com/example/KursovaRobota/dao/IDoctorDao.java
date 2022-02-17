package com.example.KursovaRobota.dao;

import com.example.KursovaRobota.models.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDoctorDao {
    ArrayList<Doctor> getDoctorsByName(String name) throws SQLException;

    ArrayList<Doctor> getDoctors() throws SQLException;
}
