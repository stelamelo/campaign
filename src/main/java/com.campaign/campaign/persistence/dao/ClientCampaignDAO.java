package com.campaign.campaign.persistence.dao;

import com.campaign.campaign.model.Campaign;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ClientCampaignDAO {

    @SqlUpdate("INSERT INTO client_campaign(client_id, campaign_id) VALUES(?, ?)")
    boolean insertCampaign(int clientId, int campaignId);

    @SqlQuery("SELECT * FROM client_campaign WHERE client_id=? AND campaign_id=?")
    @RegisterBeanMapper(Campaign.class)
    Campaign findCampaignByClientAndCampaignId(int clientId, int campaignId);
}
