package com.planning.mealsandrecipes.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "recipe_id")
    private Integer recipeId;


    @Column(name = "ingredient_id")
    private Long ingredient_id;
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

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Long ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}