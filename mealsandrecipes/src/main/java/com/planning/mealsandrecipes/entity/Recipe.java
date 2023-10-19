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
@Schema(description = "This class represents recipe.")
@Entity
@Table(name = "Recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RecipeID")
    private Integer recipeId;

    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Client client;

    @Column(name = "RecipeName")
    private String recipeName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "Instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "PreparationTime")
    private Integer preparationTime;

    @Column(name = "CookingTime")
    private Integer cookingTime;

    // Constructors, getters, and setters
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId.equals(recipe.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId);
    }
}