package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public List<Client> getAllclient() {
        return clientRepo.findAll();
    }

    // create Client rest API
    public Client createClient(Client Client) {
        return clientRepo.save(Client);
    }

    public Client getByClientId(Long id){
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Client not exist with id :" + id));
        return client;
    }
    public Client updateClientById(Client clientDetails, Long id){
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Client not exist with id :" + id));
        client.setName(clientDetails.getName());
        Client updatedClient = clientRepo.save(client);
        return updatedClient;
    }
}
