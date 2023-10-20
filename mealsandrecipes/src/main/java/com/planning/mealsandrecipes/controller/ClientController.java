package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    // Inject the ClientService for handling client-related operations.
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Define an endpoint to retrieve all clients.
    @GetMapping
    public List<Client> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK).getBody();
    }

    // Define an endpoint to retrieve a client by their ID.
    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable int id) {
        Optional<Client> client = clientService.getClientById(id);
        Client client1 = client.get();
        if (client1 != null) {
            return new ResponseEntity<>(client, HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND).getBody();
        }

    }

    // Define an endpoint to create a new client.
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // Define an endpoint to update an existing client.
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(id, updatedClient);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND).getBody();
        }
    }

    // Define an endpoint to delete a client by their ID.
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }
}
