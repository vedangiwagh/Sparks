package com.planning.mealsandrecipes.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.Before;
import static org.mockito.Mockito.*;

public class RecipeTests {

    private Recipe recipe = new Recipe();

    @Before
    public void setUp() {
        recipe = new Recipe();
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters and setters for all fields
        recipe.setRecipeId(1);
        assertEquals(1, recipe.getRecipeId().intValue());

        recipe.setClient(2);
        assertEquals(2, recipe.getClient());

        recipe.setRecipeName("Test Recipe");
        assertEquals("Test Recipe", recipe.getRecipeName());

        recipe.setDescription("Test Description");
        assertEquals("Test Description", recipe.getDescription());

        recipe.setInstructions("Test Instructions");
        assertEquals("Test Instructions", recipe.getInstructions());

        recipe.setPreparationTime(30);
        assertEquals(30, recipe.getPreparationTime().intValue());

        recipe.setCookingTime(45);
        assertEquals(45, recipe.getCookingTime().intValue());

        recipe.setMealType("Lunch");
        assertEquals("Lunch", recipe.getMealType());

        recipe.setRecipeType("Main Course");
        assertEquals("Main Course", recipe.getRecipeType());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(recipe);
    }

    @Test
    public void testParameterizedConstructor() {
        Recipe parameterizedRecipe = new Recipe(1, "Test Recipe", "Test Description",
                "Test Instructions", 30, 45, "Lunch", "Main Course");

        assertEquals(1, parameterizedRecipe.getClient());
        assertEquals("Test Recipe", parameterizedRecipe.getRecipeName());
        assertEquals("Test Description", parameterizedRecipe.getDescription());
        assertEquals("Test Instructions", parameterizedRecipe.getInstructions());
        assertEquals(30, parameterizedRecipe.getPreparationTime().intValue());
        assertEquals(45, parameterizedRecipe.getCookingTime().intValue());
        assertEquals("Lunch", parameterizedRecipe.getMealType());
        assertEquals("Main Course", parameterizedRecipe.getRecipeType());
    }

    @Test
    public void testSetRecipe() {
        Recipe otherRecipe = mock(Recipe.class);
        when(otherRecipe.getClient()).thenReturn(3);
        when(otherRecipe.getRecipeName()).thenReturn("Other Recipe");
        when(otherRecipe.getDescription()).thenReturn("Other Description");
        when(otherRecipe.getInstructions()).thenReturn("Other Instructions");
        when(otherRecipe.getPreparationTime()).thenReturn(20);
        when(otherRecipe.getCookingTime()).thenReturn(30);
        when(otherRecipe.getMealType()).thenReturn("Dinner");
        when(otherRecipe.getRecipeType()).thenReturn("Dessert");

        recipe.setRecipe(otherRecipe);

        assertEquals(3, recipe.getClient());
        assertEquals("Other Recipe", recipe.getRecipeName());
        assertEquals("Other Description", recipe.getDescription());
        assertEquals("Other Instructions", recipe.getInstructions());
        assertEquals(20, recipe.getPreparationTime().intValue());
        assertEquals(30, recipe.getCookingTime().intValue());
        assertEquals("Dinner", recipe.getMealType());
        assertEquals("Dessert", recipe.getRecipeType());
    }
}
