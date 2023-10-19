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

    public List<Client> getAllClients() {
        return (List<Client>) clientRepo.findAll();
    }

    public Optional<Client> getClientById(int id) {
        return clientRepo.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    public Client updateClient(int id, Client updatedClient) {
        if (clientRepo.existsById(id)) {
            updatedClient.setClientID(id);
            return clientRepo.save(updatedClient);
        }
        return null; // Handle the case where the client doesn't exist
    }

    public void deleteClient(int id) {
        clientRepo.deleteById(id);
    }
}

