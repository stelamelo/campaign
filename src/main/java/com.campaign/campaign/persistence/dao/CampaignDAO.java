package com.campaign.campaign.persistence.dao;

import com.campaign.campaign.model.Campaign;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Timestamp;
import java.util.List;

public interface CampaignDAO {

    @SqlUpdate("INSERT INTO campaign(name, team_id, start_date, end_date) VALUES(?, ?, ?, ?)")
    @GetGeneratedKeys("id")
    int insertCampaign(String name, int teamId, Timestamp startDate, Timestamp endDate);

    @SqlQuery("SELECT * FROM campaign WHERE start_date=?")
    @RegisterBeanMapper(Campaign.class)
    List<Campaign> findCampaignsByStartDate(Timestamp startDate);

    @SqlQuery("SELECT * FROM campaign WHERE end_date=?")
    @RegisterBeanMapper(Campaign.class)
    List<Campaign> findCampaignsByEndDate(Timestamp endDate);

    @SqlQuery("SELECT * FROM campaign WHERE team_id=?")
    @RegisterBeanMapper(Campaign.class)
    List<Campaign> findCampaignsByTeamId(int teamId);

    @SqlQuery("SELECT c.* FROM client_campaign cc INNER JOIN campaign c on cc.campaign_id = c.id WHERE cc.client_id = ?")
    @RegisterBeanMapper(Campaign.class)
    List<Campaign> findCampaignsByClientId(int clientId);

    @SqlQuery("SELECT c.* FROM client_campaign cc INNER JOIN campaign c on cc.campaign_id = c.id WHERE cc.client_id = ? AND cc.campaign_id=?")
    @RegisterBeanMapper(Campaign.class)
    Campaign findCampaignByClientAndCampaignId(int clientId, int campaignId);

    @SqlUpdate("UPDATE campaign SET end_date=? WHERE id=?")
    boolean delayEndDate(Timestamp endDate, int id);

    @SqlUpdate("DELETE FROM campaign WHERE id=?")
    boolean deleteCampaign(int campaignId);
}
