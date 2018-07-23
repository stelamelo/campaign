package com.campaign.campaign.persistence.repository;

import com.campaign.campaign.adapter.ClientRepository;
import com.campaign.campaign.model.Client;
import com.campaign.campaign.persistence.dao.ClientDAO;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JdbiClientRepository implements ClientRepository {

    private final Jdbi jdbi;

    @Autowired
    public JdbiClientRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Client insertClient(Client client) {
        ClientDAO clientDAO = jdbi.onDemand(ClientDAO.class);
        int id = clientDAO.insertClient(client.getName(), client.getEmail(), client.getBirthDate(), client.getTeamId());
        return new Client(id, client.getName(), client.getEmail(), client.getBirthDate(), client.getTeamId());
    }

    @Override
    public Client findClientByEmail(String email) {
        ClientDAO clientDAO = jdbi.onDemand(ClientDAO.class);
        return clientDAO.findClientByEmail(email);
    }
}
