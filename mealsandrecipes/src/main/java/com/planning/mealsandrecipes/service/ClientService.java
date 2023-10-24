package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.ClientRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;
    // Retrieve a list of all clients.
    public List<Client> getAllClients() {
        return (List<Client>) clientRepo.findAll();
    }

    // Retrieve a client by their ID.
    public Client getClientById(int id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Client not exist with id :" + id));
        return client;
    }

    // Create a new client.
    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    // Update an existing client by ID with the provided data.
    public Client updateClient(int id, Client clientDetails) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Client not exist with id :" + id));
        client.setName(clientDetails.getName());
        Client updatedClient = clientRepo.save(client);
        System.out.println("CLIENT NOT FOUND");
        return updatedClient;
    }

    // Delete a client by their ID.
    public void deleteClient(int id) {
        clientRepo.deleteById(id);
    }
}
