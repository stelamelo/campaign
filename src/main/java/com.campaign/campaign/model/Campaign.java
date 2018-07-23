package com.campaign.campaign.model;

import java.sql.Timestamp;

public class Campaign {

    private int id;
    private String name;
    private int teamId;
    private Timestamp startDate;
    private Timestamp endDate;

    public Campaign() {}

    public Campaign(int id, String name, int teamId, Timestamp startDate, Timestamp endDate) {
        this.setId(id);
        this.setName(name);
        this.setTeamId(teamId);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public Campaign(String name, int teamId, Timestamp startDate, Timestamp endDate) {
        this.setName(name);
        this.setTeamId(teamId);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public String getName() {
        return name;
    }

    public int getTeamId() {
        return teamId;
    }

    public Timestamp getStartDate() { return startDate; }

    public Timestamp getEndDate() {
        return endDate;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
