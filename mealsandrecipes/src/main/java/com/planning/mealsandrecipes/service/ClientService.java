package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.repository.ClientRepo;

import java.util.List;

public class ClientService {
    public List<Client> getClient()
    {
        ClientRepo clientRepo = new ClientRepo();
        return clientRepo.getClientsFromDB();
    }
}
