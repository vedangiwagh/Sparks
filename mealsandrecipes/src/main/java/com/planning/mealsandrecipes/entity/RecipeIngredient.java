package com.planning.mealsandrecipes.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class RecipeIngredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Ingredient ingredient;

    private String quantity;

    // Constructors, getters, and setters
    // Omitted for brevity
}