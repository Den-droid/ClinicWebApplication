package com.example.KursovaRobota.models;

public class Doctor {
    private int id;
    private String fullname;
    private String specialty;

    public Doctor() {
    }

    public Doctor(int id) {
        this.id = id;
    }

    public Doctor(int id, String fullname, String specialty) {
        this.id = id;
        this.fullname = fullname;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getSpecialty() {
        return specialty;
    }
}
