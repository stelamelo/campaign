package com.campaign.campaign.adapter;

import com.campaign.campaign.model.Campaign;

import java.sql.Timestamp;
import java.util.List;

public interface CampaignRepository {

    Campaign insertCampaign(Campaign campaign);

    List<Campaign> findCampaignsByStartDate(Timestamp startDate);

    List<Campaign> findCampaignsByEndDate(Timestamp endDate);

    List<Campaign> findCampaignsByTeamId(int teamId);

    Campaign findCampaignsByClientAndCampaignId(int clientId, int campaignId);

    boolean delayEndDate(Campaign campaign);

    boolean deleteCampaign(int campaignId);
}
