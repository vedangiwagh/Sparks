package com.planning.mealsandrecipes.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class RecipeRequestTests {

    @Test
    public void testGetSetMealType() {
        String mealType = "Lunch";
        RecipeRequest recipeRequest = new RecipeRequest();

        recipeRequest.setMealType(mealType);

        assertEquals(mealType, recipeRequest.getMealType());
    }

    @Test
    public void testGetSetRecipeType() {
        String recipeType = "Vegetarian";
        RecipeRequest recipeRequest = new RecipeRequest();

        recipeRequest.setRecipeType(recipeType);

        assertEquals(recipeType, recipeRequest.getRecipeType());
    }

    @Test
    public void testGetSetClient() {
        int clientId = 123;
        RecipeRequest recipeRequest = new RecipeRequest();

        recipeRequest.setClient(clientId);

        assertEquals(clientId, recipeRequest.getClient());
    }
}
