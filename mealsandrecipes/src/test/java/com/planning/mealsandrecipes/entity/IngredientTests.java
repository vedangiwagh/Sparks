package com.planning.mealsandrecipes.entity;

import org.junit.jupiter.api.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class IngredientTests {

    private Ingredient ingredient = new Ingredient();

    @Before
    public void setUp() {
        ingredient = new Ingredient();
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters and setters for all fields
        ingredient.setId(1L);
        assertEquals(1L, ingredient.getId().longValue());

        ingredient.setName("Test Ingredient");
        assertEquals("Test Ingredient", ingredient.getName());

        ingredient.setCalories(100.0);
        assertEquals(100.0, ingredient.getCalories(), 0.0);

        ingredient.setFat(10.0);
        assertEquals(10.0, ingredient.getFat(), 0.0);

        ingredient.setCarbohydrates(20.0);
        assertEquals(20.0, ingredient.getCarbohydrates(), 0.0);

        ingredient.setFiber(5.0);
        assertEquals(5.0, ingredient.getFiber(), 0.0);

        ingredient.setSugar(8.0);
        assertEquals(8.0, ingredient.getSugar(), 0.0);

        ingredient.setProtein(15.0);
        assertEquals(15.0, ingredient.getProtein(), 0.0);

        ingredient.setSodium(500.0);
        assertEquals(500.0, ingredient.getSodium(), 0.0);
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(ingredient);
    }

    @Test
    public void testParameterizedConstructor() {
        Ingredient parameterizedIngredient = new Ingredient(
                "Test Ingredient", 100.0, 10.0, 20.0, 5.0, 8.0, 15.0, 500.0);

        assertEquals("Test Ingredient", parameterizedIngredient.getName());
        assertEquals(100.0, parameterizedIngredient.getCalories(), 0.0);
        assertEquals(10.0, parameterizedIngredient.getFat(), 0.0);
        assertEquals(20.0, parameterizedIngredient.getCarbohydrates(), 0.0);
        assertEquals(5.0, parameterizedIngredient.getFiber(), 0.0);
        assertEquals(8.0, parameterizedIngredient.getSugar(), 0.0);
        assertEquals(15.0, parameterizedIngredient.getProtein(), 0.0);
        assertEquals(500.0, parameterizedIngredient.getSodium(), 0.0);
    }

}
