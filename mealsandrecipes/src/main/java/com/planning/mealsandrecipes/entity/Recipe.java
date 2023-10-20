package com.planning.mealsandrecipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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
