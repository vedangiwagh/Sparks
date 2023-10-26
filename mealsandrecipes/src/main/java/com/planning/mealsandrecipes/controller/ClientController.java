package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Autowired
    private ClientService clientService;

    // Define an endpoint to retrieve all clients.
    @Operation(summary = "Get a list of clients")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of Clients")
    @ApiResponse(responseCode = "404", description = "no Clients")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Define an endpoint to retrieve a client by their ID.
    @GetMapping("/{id}")
    @Operation(summary = "Get client by id")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of items by id")
    @ApiResponse(responseCode = "404", description = "Client not exist with id")
    public Client getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    // Define an endpoint to create a new client.
    @Operation(summary = "Create a new client")
    @ApiResponse(responseCode = "201", description = "client created successfully")
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // Define an endpoint to update an existing client.
    @PutMapping("/{id}")
    @Operation(summary = "Update client")
    @ApiResponse(responseCode = "201", description = "client  successfully")
    public Client updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
        return clientService.updateClient(id, updatedClient);
    }

    // Define an endpoint to delete a client by their ID.
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client")
    @ApiResponse(responseCode = "200", description = "client deleted successfully")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }
}
