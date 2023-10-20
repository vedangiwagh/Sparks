package com.planning.mealsandrecipes.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.planning.mealsandrecipes.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private ClientService clientService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllClients() throws Exception {
        // Mock the behavior of the clientService
        when(clientService.getAllClients()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    // Similarly, you can write tests for other controller methods (get by ID, create, update, delete).
}
