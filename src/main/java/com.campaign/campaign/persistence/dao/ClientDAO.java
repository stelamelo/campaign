package com.campaign.campaign.persistence.dao;

import com.campaign.campaign.model.Client;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Timestamp;

public interface ClientDAO {

    @SqlUpdate("INSERT INTO client(name, email, birth_date, team_id) VALUES(?, ?, ?, ?)")
    @GetGeneratedKeys("id")
    int insertClient(String name, String email, Timestamp birthDate, int teamId);

    @SqlQuery("SELECT * FROM client WHERE email=?")
    @RegisterBeanMapper(Client.class)
    Client findClientByEmail(String email);
}
