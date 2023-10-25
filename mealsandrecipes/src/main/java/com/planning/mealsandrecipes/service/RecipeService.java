package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    public  RecipeRepo recipeRepository;

    // Create a new recipe and save it to the repository.
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // Retrieve a recipe by its ID.
    public Recipe getRecipeById(Integer recipeId) {
        if (isDatabaseEmpty()) {
            throw new ResourceNotFoundException("No recipes found");
        }
        Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId))
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Recipe does not exist with id :" + recipeId));
        return recipe;
    }

    // Retrieve a list of all recipes.
    public List<Recipe> getAllRecipes() {
        if (isDatabaseEmpty()) {
            throw new ResourceNotFoundException("No recipes found");
        }
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    // Update an existing recipe with the provided data.
    public Recipe updateRecipe(Integer recipeId, Recipe recipeDetails) {
        Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId))
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Client not exist with id :" + recipeId));
        recipe.setRecipe(recipeDetails);
        Recipe updatedRecipe = recipeRepository.save(recipe);
        System.out.println("CLIENT NOT FOUND");
        return updatedRecipe;
    }

    public List<Recipe> findRecipesByName(String recipeName) {
        System.out.println("RECIPE NAME" + recipeName);
        return recipeRepository.findByRecipeName(recipeName);
    }
    // Delete a recipe by its ID.
    public void deleteRecipe(Integer recipeId) {
        recipeRepository.deleteById(Long.valueOf(recipeId));
    }

    public boolean isDatabaseEmpty() {
        return recipeRepository.count() == 0;
    }
}
