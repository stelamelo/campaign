package com.campaign.campaign.persistence.repository;

import com.campaign.campaign.adapter.ClientCampaignRepository;
import com.campaign.campaign.model.Campaign;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.campaign.campaign.persistence.dao.ClientCampaignDAO;

import java.util.List;

@Repository
public class JdbiClientCampaignRepository implements ClientCampaignRepository {

    private final Jdbi jdbi;

    @Autowired
    public JdbiClientCampaignRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public boolean insertClientCampaign(int clientId, int campaignId) {
        ClientCampaignDAO clientCampaignDAO = jdbi.onDemand(ClientCampaignDAO.class);
        return clientCampaignDAO.insertCampaign(clientId, campaignId);
    }

    @Override
    public List<Campaign> findAssociationByCampaignId(int campaignId) {
        ClientCampaignDAO clientCampaignDAO = jdbi.onDemand(ClientCampaignDAO.class);
        return clientCampaignDAO.findAssociationByCampaignId(campaignId);
    }
}
