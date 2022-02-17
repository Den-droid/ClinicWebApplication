package com.example.KursovaRobota.models;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int id;
    private String fullname;
    private String specialty;
    private Date date;
    private Time time;
    private int placesAvailable;

    public Appointment(int id, Date date, Time time, int placesAvailable) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.placesAvailable = placesAvailable;
    }

    public Appointment(Date date, Time time, int placesAvailable) {
        this.date = date;
        this.time = time;
        this.placesAvailable = placesAvailable;
    }

    public Appointment(int id, String fullname, String specialty, Date date, Time time, int placesAvailable) {
        this.id = id;
        this.fullname = fullname;
        this.specialty = specialty;
        this.date = date;
        this.time = time;
        this.placesAvailable = placesAvailable;
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

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getPlacesAvailable() {
        return placesAvailable;
    }
}