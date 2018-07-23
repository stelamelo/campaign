package com.campaign.service;

import com.campaign.campaign.adapter.ClientCampaignRepository;
import com.campaign.campaign.adapter.ClientRepository;
import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.model.Client;
import com.campaign.campaign.service.CampaignService;
import com.campaign.campaign.service.ClientService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    private ClientCampaignRepository clientCampaignRepository;
    private ClientRepository clientRepository;
    private CampaignService campaignService;
    private ClientService clientService;

    @Before
    public void setup() {
        clientCampaignRepository = mock(ClientCampaignRepository.class);
        clientRepository = mock(ClientRepository.class);
        campaignService = mock(CampaignService.class);
        clientService = new ClientService(campaignService, clientRepository, clientCampaignRepository);
    }

    @Test
    public void shouldInsertClientSuccessfullyAndAssociateAllCampaigns() {
        Client client = new Client(2,
                                "test",
                                "test@test.com",
                                      new Timestamp(new Date().getTime()),
                               1);

        Campaign campaign = new Campaign(1, "campaign1", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        Campaign campaign2 = new Campaign(2, "campaign2", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        campaigns.add(campaign2);

        when(clientRepository.findClientByEmail(client.getEmail())).thenReturn(null);
        when(clientRepository.insertClient(client)).thenReturn(client);
        when(campaignService.findCampaignsByTeamId(client.getTeamId())).thenReturn(campaigns);
        when(campaignService.findCampaignByClientAndCampaignId(client.getId(), campaign.getId())).thenReturn(null);
        when(clientCampaignRepository.insertClientCampaign(client.getId(), campaign.getId())).thenReturn(true);
        when(clientCampaignRepository.insertClientCampaign(client.getId(), campaign2.getId())).thenReturn(true);

        List<Campaign> expectedCampaigns = clientService.insertClient(client);

        assertEquals(campaigns.size(), expectedCampaigns.size());
        assertEquals(campaigns.get(0).getName(), expectedCampaigns.get(0).getName());
        assertEquals(campaigns.get(1).getName(), expectedCampaigns.get(1).getName());
    }

    @Test
    public void shouldInsertClientSuccessfullyAndAssociateJustOneCampaign() {
        Client client = new Client(2,
                "test",
                "test2@test.com",
                new Timestamp(new Date().getTime()),
                1);

        Campaign campaign = new Campaign(1, "campaign1", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        Campaign campaign2 = new Campaign(2, "campaign2", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);

        when(clientRepository.findClientByEmail(client.getEmail())).thenReturn(client);
        when(campaignService.findCampaignsByTeamId(client.getTeamId())).thenReturn(campaigns);
        when(campaignService.findCampaignByClientAndCampaignId(client.getId(), campaign.getId())).thenReturn(null);
        when(campaignService.findCampaignByClientAndCampaignId(client.getId(), campaign2.getId())).thenReturn(campaign2);
        when(clientCampaignRepository.insertClientCampaign(client.getId(), campaign.getId())).thenReturn(true);

        campaigns.add(campaign2);

        List<Campaign> expectedCampaigns = clientService.insertClient(client);

        assertEquals(1, expectedCampaigns.size());
        assertEquals(campaign.getName(), expectedCampaigns.get(0).getName());
    }
}
