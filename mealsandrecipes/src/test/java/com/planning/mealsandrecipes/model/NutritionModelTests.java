package com.planning.mealsandrecipes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class NutritionModelTests {

    private NutritionModel nutritionModel;

    @BeforeEach
    public void setUp() {
        nutritionModel = new NutritionModel(100.0, 10.0, 20.0, 5.0, 8.0, 15.0, 500.0);
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(100.0, nutritionModel.getCalories(), 0.0);
        assertEquals(10.0, nutritionModel.getFat(), 0.0);
        assertEquals(20.0, nutritionModel.getCarbohydrates(), 0.0);
        assertEquals(5.0, nutritionModel.getFiber(), 0.0);
        assertEquals(8.0, nutritionModel.getSugar(), 0.0);
        assertEquals(15.0, nutritionModel.getProtein(), 0.0);
        assertEquals(500.0, nutritionModel.getSodium(), 0.0);
    }

    @Test
    public void testGetSetCalories() {
        Double calories = 200.0;
        NutritionModel nutritionModel = new NutritionModel();

        nutritionModel.setCalories(calories);

        assertEquals(calories, nutritionModel.getCalories());
    }

    @Test
    public void testGetSetFat() {
        Double fat = 15.0;
        NutritionModel nutritionModel = new NutritionModel();

        nutritionModel.setFat(fat);

        assertEquals(fat, nutritionModel.getFat());
    }

    @Test
    public void testGetSetCarbohydrates() {
        Double carbohydrates = 30.0;
        NutritionModel nutritionModel = new NutritionModel();

        nutritionModel.setCarbohydrates(carbohydrates);

        assertEquals(carbohydrates, nutritionModel.getCarbohydrates());
    }

    @Test
    public void testGetSetFiber() {
        Double fiber = 5.0;
        NutritionModel nutritionModel = new NutritionModel();

        nutritionModel.setFiber(fiber);

        assertEquals(fiber, nutritionModel.getFiber());
    }

    @Test
    public void testGetSetSugar() {
        Double sugar = 10.0;
        NutritionModel nutritionModel = new NutritionModel();

        nutritionModel.setSugar(sugar);

        assertEquals(sugar, nutritionModel.getSugar());
    }

    @Test
    public void testGetSetProtein() {
        Double protein = 25.0;
        NutritionModel nutritionModel = new NutritionModel();

        nutritionModel.setProtein(protein);

        assertEquals(protein, nutritionModel.getProtein());
    }

    @Test
    public void testGetSetSodium() {
        Double sodium = 100.0;
        NutritionModel nutritionModel = new NutritionModel();

        nutritionModel.setSodium(sodium);

        assertEquals(sodium, nutritionModel.getSodium());
    }
}
