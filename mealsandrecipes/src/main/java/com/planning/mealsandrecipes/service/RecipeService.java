package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private  RecipeRepo recipeRepository;

    // Create a new recipe and save it to the repository.
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // Retrieve a recipe by its ID.
    public Recipe getRecipeById(Integer recipeId) {
        return recipeRepository.findById(Long.valueOf(recipeId)).orElse(null);
    }

    // Retrieve a list of all recipes.
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Update an existing recipe with the provided data.
    public void updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    // Delete a recipe by its ID.
    public void deleteRecipe(Integer recipeId) {
        recipeRepository.deleteById(Long.valueOf(recipeId));
    }
}
