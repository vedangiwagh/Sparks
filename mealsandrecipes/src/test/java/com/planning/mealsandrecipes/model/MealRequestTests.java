package com.planning.mealsandrecipes.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MealRequestTests {

    @Test
    public void testGetSetMealType() {
        String mealType = "Breakfast";
        MealRequest mealRequest = new MealRequest();

        mealRequest.setMealType(mealType);

        assertEquals(mealType, mealRequest.getMealType());
    }

    @Test
    public void testGetSetRecipeType() {
        String recipeType = "Vegetarian";
        MealRequest mealRequest = new MealRequest();

        mealRequest.setRecipeType(recipeType);

        assertEquals(recipeType, mealRequest.getRecipeType());
    }

    @Test
    public void testGetSetDietRestriction() {
        ArrayList<String> dietRestriction = new ArrayList<>();
        dietRestriction.add("Avocado");
        dietRestriction.add("Black Beans");
        MealRequest mealRequest = new MealRequest();

        mealRequest.setDietRestriction(dietRestriction);

        assertEquals(dietRestriction, mealRequest.getDietRestriction());
    }

    @Test
    public void testGetSetCalorieLimit() {
        int calorieLimit = 2000;
        MealRequest mealRequest = new MealRequest();

        mealRequest.setCalorieLimit(calorieLimit);

        assertEquals(calorieLimit, mealRequest.getCalorieLimit());
    }

    @Test
    public void testGetSetClientId() {
        int clientId = 123;
        MealRequest mealRequest = new MealRequest();

        mealRequest.setClientId(clientId);

        assertEquals(clientId, mealRequest.getClientId());
    }

    @Test
    public void testGetSetClientName() {
        String clientName = "test";
        MealRequest mealRequest = new MealRequest();

        mealRequest.setClientName(clientName);

        assertEquals(clientName, mealRequest.getClientName());
    }
}
