package com.planning.mealsandrecipes.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class RecipeIngredientTests {

    private RecipeIngredient recipeIngredient;

    @BeforeEach
    public void setUp() {
        recipeIngredient = new RecipeIngredient();
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters and setters for all fields
        recipeIngredient.setId(1);
        assertEquals(1, recipeIngredient.getId().intValue());

        recipeIngredient.setRecipeId(2);
        assertEquals(2, recipeIngredient.getRecipeId());

        recipeIngredient.setIngredient_id(3L);
        assertEquals(3L, recipeIngredient.getIngredient_id().longValue());

        recipeIngredient.setQuantity("100g");
        assertEquals("100g", recipeIngredient.getQuantity());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(recipeIngredient);
    }

}
