package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// RecipeRepository is a repository interface for the Recipe entity.
// It extends JpaRepository to provide access to CRUD operations for recipes.
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    // Custom query method to retrieve a list of recipes ordered by the updated timestamp in descending order.
}
