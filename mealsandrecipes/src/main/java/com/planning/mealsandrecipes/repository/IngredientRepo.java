package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
    // You can define custom query methods here if needed
    Optional<Ingredient> findById(Long id);

}

