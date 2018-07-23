package com.campaign.persistence.repository;

import com.campaign.campaign.adapter.CampaignRepository;
import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.persistence.repository.JdbiCampaignRepository;
import com.campaign.persistence.conf.PersistenceConfigurationTest;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class JdbiCampaignRepositoryTest extends PersistenceConfigurationTest {

    private CampaignRepository campaignRepository;

    @Before
    public void setup() {
        campaignRepository = new JdbiCampaignRepository(super.jdbi);
    }

//    @Test
//    public void shouldInsertCampaignSuccessfully() {
//        Campaign campaign = new Campaign();
//        campaign.setName("campaign-test");
//        campaign.setStartDate(new Timestamp(new Date().getTime()));
//        campaign.setEndDate(new Timestamp(new Date().getTime()));
//        campaign.setTeamId(1);
//
//        Campaign expected = campaignRepository.insertCampaign(campaign);
//
//        assertEquals(1, expected.getId());
//    }
}
