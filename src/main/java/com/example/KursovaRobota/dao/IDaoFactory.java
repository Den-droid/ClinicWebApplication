package com.example.KursovaRobota.dao;

public interface IDaoFactory {
    IDoctorDao getDoctorDao();

    ITimetableDao getTimetableDao();

    IUserDao getUserDao();

    default void loadDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
