package com.campaign.campaign.service;

import com.campaign.campaign.adapter.ClientCampaignRepository;
import com.campaign.campaign.adapter.ClientRepository;
import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final CampaignService campaignService;
    private final ClientRepository clientRepository;
    private final ClientCampaignRepository clientCampaignRepository;

    @Autowired
    public ClientService(CampaignService campaignService,
                         ClientRepository clientRepository,
                         ClientCampaignRepository clientCampaignRepository) {
        this.campaignService = campaignService;
        this.clientRepository = clientRepository;
        this.clientCampaignRepository = clientCampaignRepository;
    }

    public List<Campaign> insertClient(Client client) {
        Client existentClient = clientRepository.findClientByEmail(client.getEmail());
        if (existentClient == null) {
            existentClient = clientRepository.insertClient(client);
        }

        List<Campaign> campaignsByTeam = campaignService.findCampaignsByTeamId(existentClient.getTeamId());
        List<Campaign> newCampaigns = new ArrayList<>();

        for (Campaign campaign : campaignsByTeam) {
            Campaign campaignAlreadyAssociated = campaignService.findCampaignByClientAndCampaignId(existentClient.getId(), campaign.getId());
            if (campaignAlreadyAssociated == null) {
                clientCampaignRepository.insertClientCampaign(existentClient.getId(), campaign.getId());
                newCampaigns.add(campaign);
            }
        }

        return newCampaigns;
    }
}
