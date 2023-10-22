package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// IngredientRepository is a repository interface for the Ingredient entity.
// It extends JpaRepository to provide access to CRUD operations for ingredients.
@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {

    // Custom query method to check if an ingredient with a given code exists.
}
