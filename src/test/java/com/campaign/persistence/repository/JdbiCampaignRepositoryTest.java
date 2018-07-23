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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class JdbiCampaignRepositoryTest extends PersistenceConfigurationTest {

    private ClientRepository clientRepository;
    private CampaignRepository campaignRepository;
    private ClientCampaignRepository clientCampaignRepository;

    @Before
    public void setup() {
        clientRepository = new JdbiClientRepository(super.jdbi);
        campaignRepository = new JdbiCampaignRepository(super.jdbi);
        clientCampaignRepository = new JdbiClientCampaignRepository(super.jdbi);
    }

    @Test
    public void shouldInsertCampaignSuccessfully() {
        Campaign campaign = new Campaign("campaign-test", 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        Campaign actual = campaignRepository.insertCampaign(campaign);

        assertEquals(campaign.getName(), actual.getName());
        assertEquals(campaign.getTeamId(), actual.getTeamId());
    }

    @Test
    public void shouldFindCampaignByTeamIdSuccessfully() {
        Campaign campaign = new Campaign("campaign-test2", 2, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        campaignRepository.insertCampaign(campaign);

        List<Campaign> found = campaignRepository.findCampaignsByTeamId(campaign.getTeamId());

        assertEquals(1, found.size());
        assertEquals(campaign.getTeamId(), found.get(0).getTeamId());
        assertEquals(campaign.getName(), found.get(0).getName());
    }

    @Test
    public void shouldReturnEmptyListWhenCannotFindCampaignByTeamId() {
        List<Campaign> found = campaignRepository.findCampaignsByTeamId(20);

        assertEquals(0, found.size());
    }

    @Test
    public void shouldFindCampaignByClientAndCampaignIdSuccessfully() {
        Campaign campaign = new Campaign("campaign-test3", 3, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        campaign = campaignRepository.insertCampaign(campaign);

        Client client = new Client("Arya", "arya@stark.com.br", new Timestamp(new Date().getTime()), 3);
        client = clientRepository.insertClient(client);

        clientCampaignRepository.insertClientCampaign(client.getId(), campaign.getId());

        Campaign found = campaignRepository.findCampaignsByClientAndCampaignId(client.getId(), campaign.getId());

        assertEquals(campaign.getId(), found.getId());
        assertEquals(campaign.getName(), found.getName());
    }

    @Test
    public void shouldReturnNullWhenCannotFindCampaignByClientAndCampaignId() {
        Campaign found = campaignRepository.findCampaignsByClientAndCampaignId(50, 50);

        assertNull(found);
    }

    @Test
    public void shouldReturnTrueDeleteCampaignSuccessfully() {
        Campaign campaign = new Campaign("campaign-test5", 5, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        campaign = campaignRepository.insertCampaign(campaign);

        boolean deleted = campaignRepository.deleteCampaign(campaign.getId());

        assertTrue(deleted);
    }

    @Test
    public void shouldReturnFalseWhenTryDeleteInexistentCampaign() {
        boolean deleted = campaignRepository.deleteCampaign(8);

        assertFalse(deleted);
    }

    @Test
    public void shouldFindCampaignsByStartDateSuccessfully() throws ParseException {
        Timestamp startDate = this.getTimestampGivenString("2018-07-22 14:50:52");

        Campaign campaign = new Campaign("campaign-test", 1, startDate, new Timestamp(new Date().getTime()));
        campaign = campaignRepository.insertCampaign(campaign);

        List<Campaign> found = campaignRepository.findCampaignsByStartDate(startDate);

        assertEquals(1, found.size());
        assertEquals(campaign.getStartDate(), found.get(0).getStartDate());
    }

    @Test
    public void shouldReturnEmptyListWhenCannotFindCampaignsByStartDate() throws ParseException {
        Timestamp startDate = this.getTimestampGivenString("2018-07-23 14:50:52");

        List<Campaign> found = campaignRepository.findCampaignsByStartDate(startDate);

        assertEquals(0, found.size());
    }

    @Test
    public void shouldFindCampaignsByEndDateSuccessfully() throws ParseException {
        Timestamp endDate = this.getTimestampGivenString("2018-07-30 14:50:52");

        Campaign campaign = new Campaign("campaign-test", 1, new Timestamp(new Date().getTime()), endDate);
        campaign = campaignRepository.insertCampaign(campaign);

        List<Campaign> found = campaignRepository.findCampaignsByEndDate(endDate);

        assertEquals(1, found.size());
        assertEquals(campaign.getEndDate(), found.get(0).getEndDate());
    }

    @Test
    public void shouldReturnEmptyListWhenCannotFindCampaignsByEndDate() throws ParseException {
        Timestamp endDate = this.getTimestampGivenString("2018-07-31 14:50:52");

        List<Campaign> found = campaignRepository.findCampaignsByEndDate(endDate);

        assertEquals(0, found.size());
    }

    @Test
    public void shouldDelayEndDateSuccessfully() throws ParseException {
        Timestamp endDate = this.getTimestampGivenString("2018-07-31 14:50:52");
        Timestamp newEndDate = this.getTimestampGivenString("2018-08-01 14:50:52");

        Campaign campaign = new Campaign("campaign-test", 1, new Timestamp(new Date().getTime()), endDate);
        campaign = campaignRepository.insertCampaign(campaign);

        campaign.setEndDate(newEndDate);
        boolean delayed = campaignRepository.delayEndDate(campaign);

        assertTrue(delayed);
    }

    private Timestamp getTimestampGivenString(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(date));
        return new Timestamp(calendar.getTimeInMillis());
    }
}
