package com.planning.mealsandrecipes.Controller;

import com.planning.mealsandrecipes.controller.ClientController;
import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testGetAllClients() throws Exception {
        List<Client> clients = new ArrayList<>();
        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(clientService, times(1)).getAllClients();
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testGetClientById() throws Exception {
        Client client = new Client();
        client.setClientID(1);
        when(clientService.getClientById(1)).thenReturn(client);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientID", is(1)));

        verify(clientService, times(1)).getClientById(1);
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client();
        client.setClientID(1);
        when(clientService.createClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientID", is(1)));

        verify(clientService, times(1)).createClient(any(Client.class));
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testUpdateClient() throws Exception {
        Client client = new Client();
        client.setClientID(1);
        when(clientService.updateClient(eq(1), any(Client.class))).thenReturn(client);

        mockMvc.perform(put("/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientID", is(1)));

        verify(clientService, times(1)).updateClient(eq(1), any(Client.class));
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testDeleteClient() throws Exception {
        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());

        verify(clientService, times(1)).deleteClient(1);
        verifyNoMoreInteractions(clientService);
    }
}
