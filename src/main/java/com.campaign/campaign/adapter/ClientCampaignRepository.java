package com.campaign.campaign.adapter;

import com.campaign.campaign.model.Campaign;

import java.util.List;

public interface ClientCampaignRepository {

    boolean insertClientCampaign(int clientId, int campaignId);

    List<Campaign> findAssociationByCampaignId(int campaignId);
}
