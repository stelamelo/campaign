package com.campaign.campaign.model;

import java.sql.Timestamp;

public class Client {

    private int id;
    private String name;
    private String email;
    private Timestamp birthDate;
    private int teamId;

    public Client() {}

    public Client(int id, String name, String email, Timestamp birthDate, int teamId) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setBirthDate(birthDate);
        this.setTeamId(teamId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
