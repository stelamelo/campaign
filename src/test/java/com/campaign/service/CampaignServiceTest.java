package com.campaign.service;

import com.campaign.campaign.adapter.CampaignRepository;
import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.service.CampaignService;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CampaignServiceTest {

    private CampaignService campaignService;
    private CampaignRepository campaignRepository;

    @Before
    public void setup() {
        campaignRepository = mock(CampaignRepository.class);
        campaignService = new CampaignService(campaignRepository);
    }

    @Test
    public void shouldInsertCampaignSuccessfully() {
        Campaign campaign = new Campaign(1, "campaign1", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        when(campaignRepository.findCampaignsByStartDate(any())).thenReturn(new ArrayList<>());
        when(campaignRepository.insertCampaign(campaign)).thenReturn(campaign);

        Campaign actualCampaign = campaignService.insertCampaign(campaign);

        assertEquals(campaign.getId(), actualCampaign.getId());
        assertEquals(campaign.getName(), actualCampaign.getName());
    }

    @Test
    public void shouldDeleteCampaignSuccessfully() {
        when(campaignRepository.deleteCampaign(1)).thenReturn(true);
        assertTrue(campaignService.deleteCampaign(1));
    }

    @Test
    public void shouldFindCampaignsByTeamIdSuccessfully() {
        Campaign campaign = new Campaign(1, "campaign1", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);

        when(campaignRepository.findCampaignsByTeamId(1)).thenReturn(campaigns);

        List<Campaign> campaignsByTeamId = campaignService.findCampaignsByTeamId(1);

        assertEquals(1, campaignsByTeamId.size());
        assertEquals(campaign.getName(), campaignsByTeamId.get(0).getName());
    }

    @Test
    public void shouldReturnEmptyListWhenCannotFindCampaignsByTeamId() {
        when(campaignRepository.findCampaignsByTeamId(1)).thenReturn(new ArrayList<>());

        List<Campaign> campaignsByTeamId = campaignService.findCampaignsByTeamId(1);

        assertEquals(0, campaignsByTeamId.size());
    }

    @Test
    public void shouldFindCampaignByClientAndCampaignIdSuccessfully() {
        Campaign campaign = new Campaign(1, "campaign1", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        when(campaignRepository.findCampaignsByClientAndCampaignId(1, 1)).thenReturn(campaign);

        Campaign campaignFound = campaignService.findCampaignByClientAndCampaignId(1, 1);

        assertEquals(campaign.getId(), campaignFound.getId());
        assertEquals(campaign.getName(), campaignFound.getName());
    }
}
