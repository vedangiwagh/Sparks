import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.repository.ClientRepo;
import com.planning.mealsandrecipes.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientServiceTest {

    @Mock
    private ClientRepo clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllClients() {
        // Mock the behavior of the clientRepository
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Client> clients = clientService.getAllClients();
        assertTrue(clients.isEmpty());
    }

    @Test
    public void testGetClientById() {
        int clientId = 1;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientById(clientId);
        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }

    @Test
    public void testCreateClient() {
        Client client = new Client();
        when(clientRepository.save(client)).thenReturn(client);

        Client createdClient = clientService.createClient(client);
        assertEquals(client, createdClient);
    }

    @Test
    public void testUpdateClient() {
        int clientId = 1;
        Client updatedClient = new Client();
        when(clientRepository.existsById(clientId)).thenReturn(true);
        when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

        Client result = clientService.updateClient(clientId, updatedClient);
        assertEquals(updatedClient, result);
    }

    @Test
    public void testDeleteClient() {
        int clientId = 1;

        // No need to mock for delete since we are not returning anything

        assertDoesNotThrow(() -> clientService.deleteClient(clientId));
    }
}
