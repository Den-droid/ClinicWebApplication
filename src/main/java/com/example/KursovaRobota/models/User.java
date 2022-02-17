package com.example.KursovaRobota.models;

public class User {
    private int id;
    private String login;
    private String fullname;
    private String type;
    private String password;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String login, String fullname, String type, String password) {
        this.login = login;
        this.fullname = fullname;
        this.type = type;
        this.password = password;
    }

    public User(int id, String login, String fullname, String type) {
        this.id = id;
        this.login = login;
        this.fullname = fullname;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFullname() {
        return fullname;
    }

    public String getType() {
        return type;
    }
}
