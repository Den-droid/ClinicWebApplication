package com.example.KursovaRobota.models;

public class ConnectionParameters {
    private final String url = "jdbc:mysql://localhost:3306/kursachdb?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "password";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
