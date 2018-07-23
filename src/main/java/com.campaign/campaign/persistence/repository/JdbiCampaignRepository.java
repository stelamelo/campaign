package com.campaign.campaign.persistence.repository;

import com.campaign.campaign.adapter.CampaignRepository;
import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.persistence.dao.CampaignDAO;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class JdbiCampaignRepository implements CampaignRepository {

    private final Jdbi jdbi;

    @Autowired
    public JdbiCampaignRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Campaign insertCampaign(Campaign campaign) {
        CampaignDAO campaignDAO = jdbi.onDemand(CampaignDAO.class);
        int id = campaignDAO.insertCampaign(campaign.getName(),
                                            campaign.getTeamId(),
                                            campaign.getStartDate(),
                                            campaign.getEndDate());
        return new Campaign(id,
                            campaign.getName(),
                            campaign.getTeamId(),
                            campaign.getStartDate(),
                            campaign.getEndDate());
    }

    @Override
    public List<Campaign> findCampaignsByStartDate(Timestamp startDate) {
        CampaignDAO campaignDAO = jdbi.onDemand(CampaignDAO.class);
        return campaignDAO.findCampaignsByStartDate(startDate);
    }

    @Override
    public List<Campaign> findCampaignsByEndDate(Timestamp endDate) {
        CampaignDAO campaignDAO = jdbi.onDemand(CampaignDAO.class);
        return campaignDAO.findCampaignsByEndDate(endDate);
    }

    @Override
    public List<Campaign> findCampaignsByTeamId(int teamId) {
        CampaignDAO campaignDAO = jdbi.onDemand(CampaignDAO.class);
        return campaignDAO.findCampaignsByTeamId(teamId);
    }

    @Override
    public Campaign findCampaignsByClientAndCampaignId(int clientId, int campaignId) {
        CampaignDAO campaignDAO = jdbi.onDemand(CampaignDAO.class);
        return campaignDAO.findCampaignByClientAndCampaignId(clientId, campaignId);
    }

    @Override
    public boolean delayEndDate(Campaign campaign) {
        CampaignDAO campaignDAO = jdbi.onDemand(CampaignDAO.class);
        return campaignDAO.delayEndDate(campaign.getEndDate(), campaign.getId());
    }

    @Override
    public boolean deleteCampaign(int campaignId) {
        CampaignDAO campaignDAO = jdbi.onDemand(CampaignDAO.class);
        return campaignDAO.deleteCampaign(campaignId);
    }
}
