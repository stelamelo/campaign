package com.campaign.campaign.controller;

import com.campaign.campaign.adapter.CampaignRepository;
import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @RequestMapping(value = "/insertCampaign", method = RequestMethod.POST)
    @ResponseBody
    public Campaign insertCampaign(@RequestBody Campaign campaign) {
        return campaignService.insertCampaign(campaign);
    }

    @RequestMapping(value = "/deleteCampaign/{id}", method = RequestMethod.GET)
    public boolean deleteCampaign(@PathVariable("id") int id) {
        return campaignService.deleteCampaign(id);
    }


}
