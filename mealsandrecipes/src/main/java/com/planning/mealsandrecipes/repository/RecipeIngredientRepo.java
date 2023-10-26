package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepo extends JpaRepository<RecipeIngredient, Integer> {

    List<RecipeIngredient> findByRecipeId(@Param("recipeId")Integer recipeId);
    // Custom query method to check if an recipeIngredient with a given code exists.
}
