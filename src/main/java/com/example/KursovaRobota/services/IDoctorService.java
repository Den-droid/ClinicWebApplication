package com.example.KursovaRobota.services;

import com.example.KursovaRobota.models.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDoctorService {
    ArrayList<Doctor> getAll() throws SQLException;

    ArrayList<Doctor> getByName(String name) throws SQLException;
}
