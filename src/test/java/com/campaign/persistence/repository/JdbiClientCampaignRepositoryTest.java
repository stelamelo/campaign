package com.campaign.persistence.repository;

import com.campaign.campaign.adapter.CampaignRepository;
import com.campaign.campaign.adapter.ClientCampaignRepository;
import com.campaign.campaign.adapter.ClientRepository;
import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.model.Client;
import com.campaign.campaign.persistence.repository.JdbiCampaignRepository;
import com.campaign.campaign.persistence.repository.JdbiClientCampaignRepository;
import com.campaign.campaign.persistence.repository.JdbiClientRepository;
import com.campaign.persistence.conf.PersistenceConfigurationTest;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class JdbiClientCampaignRepositoryTest extends PersistenceConfigurationTest {

    private ClientCampaignRepository clientCampaignRepository;
    private CampaignRepository campaignRepository;
    private ClientRepository clientRepository;

    @Before
    public void setup() {
        clientCampaignRepository = new JdbiClientCampaignRepository(super.jdbi);
        campaignRepository = new JdbiCampaignRepository(super.jdbi);
        clientRepository = new JdbiClientRepository(super.jdbi);
    }

    @Test
    public void shouldInsertClientCampaignSuccessfully() {
        Campaign campaign = new Campaign("campaign-test2", 2, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        campaign = campaignRepository.insertCampaign(campaign);

        Client client = new Client("ron", "ron@wesley.com.br", new Timestamp(new Date().getTime()), 8);
        client = clientRepository.insertClient(client);

        boolean inserted = clientCampaignRepository.insertClientCampaign(client.getId(), campaign.getId());

        assertTrue(inserted);
    }
}
