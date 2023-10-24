package com.planning.mealsandrecipes.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTests {

    @Test
    public void testClientEntity() {
        // Create a new Client instance
        Client client = new Client("John Doe");

        // Verify that the name is set correctly
        assertEquals("John Doe", client.getName());

        // Modify the name
        client.setName("Jane Smith");

        // Verify that the name is updated
        assertEquals("Jane Smith", client.getName());
    }
}
