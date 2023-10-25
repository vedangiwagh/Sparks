package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepo extends JpaRepository<RecipeIngredient, Long> {

    // Custom query method to check if an recipeIngredient with a given code exists.
}
