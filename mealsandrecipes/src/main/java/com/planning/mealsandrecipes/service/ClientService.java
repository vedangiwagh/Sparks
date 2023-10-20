package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.repository.ClientRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    // Retrieve a list of all clients.
    public List<Client> getAllClients() {
        return (List<Client>) clientRepo.findAll();
    }

    // Retrieve a client by their ID.
    public Optional<Client> getClientById(int id) {
        return clientRepo.findById(id);
    }

    // Create a new client.
    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    // Update an existing client by ID with the provided data.
    public Client updateClient(int id, Client updatedClient) {
        if (clientRepo.existsById(id)) {
            // Optionally, set the client's ID if needed: updatedClient.setClientID(id);
            return clientRepo.save(updatedClient);
        }
        return null; // Handle the case where the client doesn't exist
    }

    // Delete a client by their ID.
    public void deleteClient(int id) {
        clientRepo.deleteById(id);
    }
}
