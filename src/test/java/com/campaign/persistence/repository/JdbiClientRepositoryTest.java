package com.campaign.persistence.repository;

import com.campaign.campaign.adapter.ClientRepository;
import com.campaign.campaign.model.Client;
import com.campaign.campaign.persistence.repository.JdbiClientRepository;
import com.campaign.persistence.conf.PersistenceConfigurationTest;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JdbiClientRepositoryTest extends PersistenceConfigurationTest {

    private ClientRepository clientRepository;

    @Before
    public void setup() {
        clientRepository = new JdbiClientRepository(super.jdbi);
    }

    @Test
    public void shouldInsertClientSuccessfully() {
        Client client = new Client("jon", "jon@snow.com.br", new Timestamp(new Date().getTime()), 7);
        Client createdClient = clientRepository.insertClient(client);

        assertEquals(client.getEmail(), createdClient.getEmail());
    }

    @Test
    public void shouldFindClientByEmailSuccessfully() {
        Client client = new Client("tom", "tom@riddle.com.br", new Timestamp(new Date().getTime()), 8);
        client = clientRepository.insertClient(client);

        Client found = clientRepository.findClientByEmail(client.getEmail());

        assertEquals(client.getId(), found.getId());
        assertEquals(client.getEmail(), found.getEmail());
    }

    @Test
    public void shouldReturnNullWhenCannotFindClient() {
        Client found = clientRepository.findClientByEmail("hermione@granger.com.br");

        assertNull(found);
    }
}
