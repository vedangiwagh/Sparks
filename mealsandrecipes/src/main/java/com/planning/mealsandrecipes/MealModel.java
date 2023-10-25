package com.planning.mealsandrecipes;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.Recipe;

import java.util.List;

public class MealModel{
    public MealModel() {
    }

    Recipe recipe;
    List<Ingredient> ingredientList;
    float nutrionalValue;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public float getNutrionalValue() {
        return nutrionalValue;
    }

    public void setNutrionalValue(float nutrionalValue) {
        this.nutrionalValue = nutrionalValue;
    }
}
