package com.planning.mealsandrecipes.model;

import com.planning.mealsandrecipes.entity.Recipe;

import java.util.List;

public class MealModel{
    public MealModel() {
    }

    Recipe recipe;
    List<String> ingredientList;
    float nutrionalValue;
    NutritionModel nutritionModel;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<String> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public float getNutrionalValue() {
        return nutrionalValue;
    }

    public void setNutrionalValue(float nutrionalValue) {
        this.nutrionalValue = nutrionalValue;
    }

    public NutritionModel getNutritionModel() {
        return nutritionModel;
    }

    public void setNutritionModel(NutritionModel nutritionModel) {
        this.nutritionModel = nutritionModel;
    }
}
