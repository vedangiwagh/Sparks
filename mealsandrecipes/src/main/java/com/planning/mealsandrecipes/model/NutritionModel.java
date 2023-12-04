package com.planning.mealsandrecipes.model;

public class NutritionModel {
    private Double calories; // Caloric content
    private Double fat; // Fat content
    private Double carbohydrates; // Carbohydrate content
    private Double fiber; // Dietary fiber content
    private Double sugar; // Sugar content
    private Double protein; // Protein content
    private Double sodium; // Sodium content

    public NutritionModel() {
    }

    public NutritionModel(Double calories, Double fat, Double carbohydrates, Double fiber, Double sugar, Double protein, Double sodium) {
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.sugar = sugar;
        this.protein = protein;
        this.sodium = sodium;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }


}
