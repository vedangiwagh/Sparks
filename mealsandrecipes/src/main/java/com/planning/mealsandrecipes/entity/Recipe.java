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
    @Column(name = "RecipeID")
    private Integer recipeId; // Unique identifier for the recipe

    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Client client; // Client associated with the recipe

    @Column(name = "RecipeName")
    private String recipeName; // Name of the recipe

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description; // Recipe description

    @Column(name = "Instructions", columnDefinition = "TEXT")
    private String instructions; // Cooking instructions

    @Column(name = "PreparationTime")
    private Integer preparationTime; // Preparation time in minutes

    @Column(name = "CookingTime")
    private Integer cookingTime; // Cooking time in minutes

    // Constructors, getters, and setters

    public Recipe() {
    }

    public Recipe(Client client, String recipeName, String description, String instructions, Integer preparationTime, Integer cookingTime) {
        this.client = client;
        this.recipeName = recipeName;
        this.description = description;
        this.instructions = instructions;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public void setRecipe(Recipe otherRecipe) {
        this.client = otherRecipe.getClient();
        this.recipeName = otherRecipe.getRecipeName();
        this.description = otherRecipe.getDescription();
        this.instructions = otherRecipe.getInstructions();
        this.preparationTime = otherRecipe.getPreparationTime();
        this.cookingTime = otherRecipe.getCookingTime();
    }



    // Override the equals method to compare recipes by their ID.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId.equals(recipe.recipeId);
    }

    // Override the hashCode method to compute the hash code based on the recipe's ID.
    @Override
    public int hashCode() {
        return Objects.hash(recipeId);
    }
}
