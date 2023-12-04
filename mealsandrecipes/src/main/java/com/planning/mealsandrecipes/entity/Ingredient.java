package com.planning.mealsandrecipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ingredient")
@Schema(description = "This class represents ingredients used.")
public class Ingredient {

    @Id
    private Long id; // Unique identifier for the ingredient
    private String name; // Name of the ingredient
    private Double calories; // Caloric content
    private Double fat; // Fat content
    private Double carbohydrates; // Carbohydrate content
    private Double fiber; // Dietary fiber content
    private Double sugar; // Sugar content
    private Double protein; // Protein content
    private Double sodium; // Sodium content

    // Constructors, getters, and setters

    public Ingredient() {
    }

    public Ingredient(String name, Double calories, Double fat, Double carbohydrates, Double fiber, Double sugar, Double protein, Double sodium) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.sugar = sugar;
        this.protein = protein;
        this.sodium = sodium;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // Override the equals method to compare ingredients by their ID.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id.equals(that.id);
    }

    // Override the hashCode method to compute the hash code based on the ingredient's ID.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
