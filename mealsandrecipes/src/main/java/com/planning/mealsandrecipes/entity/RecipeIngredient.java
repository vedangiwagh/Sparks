package com.planning.mealsandrecipes.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient implements Serializable {
    @EmbeddedId
    private Integer recipe_id;
    @Column
    private Integer ingredient_id;
    @Column(name = "Quantity")
    private String quantity;

    // Constructors, getters, and setters
    // Omitted for brevity

    public RecipeIngredient(){

    }
//    public RecipeIngredient(int recipe_id, int ingredient_id, String quantity) {
//        this.recipe_id = recipe_id;
//        this.ingredient_id = ingredient_id;
//        this.quantity = quantity;
//    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}