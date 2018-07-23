package com.campaign.campaign.controller;

import com.campaign.campaign.model.Campaign;
import com.campaign.campaign.model.Client;
import com.campaign.campaign.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/insertClient", method = RequestMethod.POST)
    @ResponseBody
    public List<Campaign> insertClient(@RequestBody Client client) {
        return clientService.insertClient(client);
    }
}
