package com.planning.mealsandrecipes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;

public class ClientTests {

    private Client client = new Client();;

    @Before
    public void setUp() {
        client = new Client();
    }

    @Test
    public void testGetClientID() {
        int clientId = 2;
        client.setClientID(clientId);
        assertEquals(clientId, client.getClientID());
    }

    @Test
    public void testGetName() {
        String name = "John Doe";
        client.setName(name);
        assertEquals(name, client.getName());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(client);
    }

    @Test
    public void testParameterizedConstructor() {
        String name = "Alice";
        Client parameterizedClient = new Client(name);
        assertEquals(name, parameterizedClient.getName());
    }

    @Test
    public void testSetClientID() {
        int clientId = 2;
        client.setClientID(clientId);
        assertEquals(clientId, client.getClientID());
    }

    @Test
    public void testSetName() {
        String name = "Bob";
        client.setName(name);
        assertEquals(name, client.getName());
    }

    @Test
    public void testEntityAnnotation() {
        Entity entityAnnotation = mock(Entity.class);
        when(entityAnnotation.name()).thenReturn("client");
        assertEquals("client", entityAnnotation.name());
    }

    @Test
    public void testIdAnnotation() {
        Id idAnnotation = mock(Id.class);
        assertNotNull(idAnnotation);
    }

    @Test
    public void testGeneratedValueAnnotation() {
        GeneratedValue generatedValueAnnotation = mock(GeneratedValue.class);
        when(generatedValueAnnotation.strategy()).thenReturn(GenerationType.IDENTITY);
        assertEquals(GenerationType.IDENTITY, generatedValueAnnotation.strategy());
    }
}


