package com.planning.mealsandrecipes.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByOrderByUpdatedAtDesc();
}
