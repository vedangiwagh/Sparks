package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// RecipeRepository is a repository interface for the Recipe entity.
// It extends JpaRepository to provide access to CRUD operations for recipes.
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    List<Recipe> findByRecipeName(@Param("recipename")String recipeName);

    List<Recipe> findByRecipeType(@Param("recipetype")String recipeType);

    List<Recipe> findByMealType(@Param("mealtype")String mealType);

    List<Recipe> findByMealTypeAndRecipeType(@Param("mealtype")String mealType, @Param("recipename")String recipeType);

    List<Recipe> findByMealTypeAndRecipeTypeAndClient(@Param("mealtype")String mealType, @Param("recipename")String recipeType, @Param("client") Client client);




    // Custom query method to retrieve a list of recipes ordered by the updated timestamp in descending order.
}
