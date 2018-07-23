package com.campaign.campaign.service;

import com.campaign.campaign.adapter.CampaignRepository;
import com.campaign.campaign.adapter.ClientCampaignRepository;
import com.campaign.campaign.model.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ClientCampaignRepository clientCampaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, ClientCampaignRepository clientCampaignRepository) {
        this.campaignRepository = campaignRepository;
        this.clientCampaignRepository = clientCampaignRepository;
    }

    public Campaign insertCampaign(Campaign campaign) {
        List<Campaign> campaigns = campaignRepository.findCampaignsByStartDate(campaign.getStartDate());

        if (!campaigns.isEmpty()) {
            this.delayEndDate(campaign);
        }

        return campaignRepository.insertCampaign(campaign);
    }

    public boolean deleteCampaign(int campaignId) {
        List<Campaign> campaignsWithAssociation = clientCampaignRepository.findAssociationByCampaignId(campaignId);
        if(!campaignsWithAssociation.isEmpty()) {
            throw new IllegalStateException("Cannot delete. Given campaign is associated with one or more clients");
        }

        return campaignRepository.deleteCampaign(campaignId);
    }

    private void delayEndDate(Campaign campaign) {
        Timestamp newEndDate = this.incrementEndDate(campaign.getEndDate());
        while (this.existCampaignWithThisEndDate(newEndDate)) {
            newEndDate = this.incrementEndDate(newEndDate);
        }

        campaign.setEndDate(newEndDate);
        campaignRepository.delayEndDate(campaign);
    }

    private boolean existCampaignWithThisEndDate(Timestamp newEndDate) {
        List<Campaign> campaigns = campaignRepository.findCampaignsByEndDate(newEndDate);
        return !campaigns.isEmpty();
    }

    private Timestamp incrementEndDate(Timestamp endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public List<Campaign> findCampaignsByTeamId(int teamId) {
        return campaignRepository.findCampaignsByTeamId(teamId);
    }

    public Campaign findCampaignByClientAndCampaignId(int clientId, int campaignId) {
        return campaignRepository.findCampaignsByClientAndCampaignId(clientId, campaignId);
    }
}
