package com.campaign.campaign.adapter;

import com.campaign.campaign.model.Client;

public interface ClientRepository {

    Client insertClient(Client client);

    Client findClientByEmail(String email);
}
