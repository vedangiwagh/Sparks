package com.planning.mealsandrecipes.Service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.ClientRepo;
import com.planning.mealsandrecipes.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepo clientRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Client 1"));
        clients.add(new Client("Client 2"));

        when(clientRepo.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetClientById() {
        Client client = new Client("Client 1");
        when(clientRepo.findById(1)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(1);
        assertEquals("Client 1", result.getName());
    }

    @Test
    public void testGetClientByIdNotFound() {
        when(clientRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.getClientById(1));
    }

    @Test
    public void testCreateClient() {
        Client newClient = new Client("New Client");
        when(clientRepo.save(newClient)).thenReturn(newClient);

        Client result = clientService.createClient(newClient);
        assertEquals("New Client", result.getName());
    }

    @Test
    public void testUpdateClient() {
        Client existingClient = new Client("Client 1");
        when(clientRepo.findById(2)).thenReturn(Optional.of(existingClient));
        when(clientRepo.save(existingClient)).thenReturn(existingClient);

        Client updatedClient = new Client("Updated Client");
        Client result = clientService.updateClient(2, updatedClient);

        assertEquals("Updated Client", result.getName());
    }

    @Test
    public void testUpdateClientNotFound() {
        when(clientRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.updateClient(1, new Client("Updated Client")));
    }

    @Test
    public void testDeleteClient() {
        clientService.deleteClient(1);
        Mockito.verify(clientRepo, Mockito.times(1)).deleteById(1);
    }
}
