package com.planning.mealsandrecipes.model;

import com.planning.mealsandrecipes.entity.Recipe;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MealModelTests {

    @Test
    public void testGetSetRecipe() {
        Recipe recipe = new Recipe(); // You might need to set up a valid Recipe object
        MealModel mealModel = new MealModel();

        mealModel.setRecipe(recipe);

        assertEquals(recipe, mealModel.getRecipe());
    }

    @Test
    public void testGetSetIngredientList() {
        List<String> ingredientList = new ArrayList<>();
        ingredientList.add("Ingredient1");
        ingredientList.add("Ingredient2");
        MealModel mealModel = new MealModel();

        mealModel.setIngredientList(ingredientList);

        assertEquals(ingredientList, mealModel.getIngredientList());
    }

    @Test
    public void testGetSetNutritionalValue() {
        float nutritionalValue = 10.5f; // You might want to use a valid value
        MealModel mealModel = new MealModel();

        mealModel.setNutrionalValue(nutritionalValue);

        assertEquals(nutritionalValue, mealModel.getNutrionalValue(), 0.001); // Specify a delta for float comparison
    }

    @Test
    public void testGetSetNutritionModel() {
        NutritionModel nutritionModel = new NutritionModel(); // You might need to set up a valid NutritionModel object
        MealModel mealModel = new MealModel();

        mealModel.setNutritionModel(nutritionModel);

        assertEquals(nutritionModel, mealModel.getNutritionModel());
    }
}

