package com.planning.mealsandrecipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Schema(description = "This class represents a recipe.")
@Entity
@Table(name = "Recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeid")
    private Integer recipeId; // Unique identifier for the recipe

    @Column(name = "recipename")
    private String recipeName; // Name of the recipe

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Recipe description

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions; // Cooking instructions

    @Column(name = "preparationtime")
    private Integer preparationTime; // Preparation time in minutes

    @Column(name = "cookingtime")
    private Integer cookingTime; // Cooking time in minutes

    @Column(name = "mealtype")
    private String mealType;

    @Column(name = "recipetype")
    private String recipeType;

    @Column(name = "clientid")
    private int client; // Client associated with the recipe

    // Constructors, getters, and setters

    public Recipe() {
    }

    public Recipe(int client, String recipeName, String description, String instructions, Integer preparationTime, Integer cookingTime, String mealType, String recipeType) {
        this.client = client;
        this.recipeName = recipeName;
        this.description = description;
        this.instructions = instructions;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.mealType = mealType;
        this.recipeType = recipeType;
    }

    public Recipe(Client client, String sampleRecipe, String sampleDescription, String sampleInstructions, int preparationTime, int cookingTime, String breakfast, String recipeType) {
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

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

    public void setRecipe(Recipe otherRecipe) {
        this.client = otherRecipe.getClient();
        this.recipeName = otherRecipe.getRecipeName();
        this.description = otherRecipe.getDescription();
        this.instructions = otherRecipe.getInstructions();
        this.preparationTime = otherRecipe.getPreparationTime();
        this.cookingTime = otherRecipe.getCookingTime();
        this.recipeType = otherRecipe.getRecipeType();
        this.mealType = otherRecipe.getMealType();
    }

}
