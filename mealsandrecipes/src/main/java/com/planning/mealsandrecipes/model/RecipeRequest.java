package com.planning.mealsandrecipes.model;

public class RecipeRequest {
    private String mealType;
    private String recipeType;
    private int client;

    // Constructors, getters, and setters

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }
}
