package com.example.KursovaRobota.services;

import com.example.KursovaRobota.dao.IDaoFactory;
import com.example.KursovaRobota.models.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorService implements IDoctorService{
    private final IDaoFactory factory;

    public DoctorService(IDaoFactory factory) {
        this.factory = factory;
    }

    @Override
    public ArrayList<Doctor> getAll() throws SQLException {
        return factory.getDoctorDao().getDoctors();
    }

    @Override
    public ArrayList<Doctor> getByName(String name) throws SQLException {
        return factory.getDoctorDao().getDoctorsByName(name);
    }
}
