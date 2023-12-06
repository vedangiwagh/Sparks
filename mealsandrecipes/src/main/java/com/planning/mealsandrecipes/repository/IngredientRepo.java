package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// IngredientRepository is a repository interface for the Ingredient entity.
// It extends JpaRepository to provide access to CRUD operations for ingredients.
@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {

    // Custom query method to check if an ingredient with a given code exists.
    List<Ingredient> findByNameContainingIgnoreCase(@Param("name") String name);

}
