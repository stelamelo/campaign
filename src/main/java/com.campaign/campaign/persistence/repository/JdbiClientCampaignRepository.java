package com.campaign.campaign.persistence.repository;

import com.campaign.campaign.adapter.ClientCampaignRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.campaign.campaign.persistence.dao.ClientCampaignDAO;

@Repository
public class JdbiClientCampaignRepository implements ClientCampaignRepository {

    private final Jdbi jdbi;

    @Autowired
    public JdbiClientCampaignRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public void insertClientCampaign(int clientId, int campaignId) {
        ClientCampaignDAO clientCampaignDAO = jdbi.onDemand(ClientCampaignDAO.class);
        clientCampaignDAO.insertCampaign(clientId, campaignId);
    }
}
