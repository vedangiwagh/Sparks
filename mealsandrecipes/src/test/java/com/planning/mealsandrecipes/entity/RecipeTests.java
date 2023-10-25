package com.planning.mealsandrecipes.entity;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTests {

    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        // Create a sample recipe for testing
        Client client = new Client();
        recipe = new Recipe(client, "Sample Recipe", "Sample Description",
                "Sample Instructions", 30, 60);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Sample Recipe", recipe.getRecipeName());
        assertEquals("Sample Description", recipe.getDescription());
        assertEquals("Sample Instructions", recipe.getInstructions());
        assertEquals(30, recipe.getPreparationTime());
        assertEquals(60, recipe.getCookingTime());

        // Test setters
        recipe.setRecipeName("New Recipe Name");
        assertEquals("New Recipe Name", recipe.getRecipeName());

        recipe.setDescription("New Description");
        assertEquals("New Description", recipe.getDescription());

        recipe.setInstructions("New Instructions");
        assertEquals("New Instructions", recipe.getInstructions());

        recipe.setPreparationTime(45);
        assertEquals(45, recipe.getPreparationTime());

        recipe.setCookingTime(75);
        assertEquals(75, recipe.getCookingTime());
    }

}
