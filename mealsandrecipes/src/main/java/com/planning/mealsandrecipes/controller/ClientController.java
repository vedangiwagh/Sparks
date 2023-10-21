package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    // Inject the ClientService for handling client-related operations.
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private ClientService clientService;
    public ClientController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/getTuples")
    public List<String> getTuples() {
        return this.jdbcTemplate.queryForList("SELECT ClientID FROM client;").stream()
                .map(m -> m.values().toString())
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<Client> getAllclient() {

        return clientService.getAllclient();
    }

    // create Client rest API
    @PostMapping("/")
    public Client createClient(@RequestBody Client Client) {
        return clientService.createClient(Client);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getUserById(@PathVariable Long id) {

        Client client = clientService.getByClientId(id);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateUserById(@PathVariable Long id, @RequestBody Client clientDetails) {

        Client client = clientService.updateClientById(clientDetails, id);
        return ResponseEntity.ok(client);
    }


//    private final ClientService clientService;


//    @Autowired
//    public ClientController(ClientService clientService) {
//        this.clientService = clientService;
//    }
//
//    // Define an endpoint to retrieve all clients.
//    @GetMapping
//    public List<Client> getAllClients() {
//        return clientService.getAllClients();
//    }

//    // Define an endpoint to retrieve a client by their ID.
//    @GetMapping("/{id}")
//    public Optional<Client> getClientById(@PathVariable int id) {
//        return clientService.getClientById(id);
//    }
//
//    // Define an endpoint to create a new client.
//    @PostMapping
//    public Client createClient(@RequestBody Client client) {
//        return clientService.createClient(client);
//    }

    // Define an endpoint to update an existing client.
//    @PutMapping("/{id}")
//    public Client updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
//        return clientService.updateClient(id, updatedClient);
//    }
//
//    // Define an endpoint to delete a client by their ID.
//    @DeleteMapping("/{id}")
//    public void deleteClient(@PathVariable int id) {
//        clientService.deleteClient(id);
//    }
}