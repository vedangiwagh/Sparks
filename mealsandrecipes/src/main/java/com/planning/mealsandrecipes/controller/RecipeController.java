package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    // Define an endpoint to create a new recipe.
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    // Define an endpoint to retrieve a recipe by its ID.
    @GetMapping("/{recipeId}")
    public Recipe getRecipe(@PathVariable Integer recipeId) {
        return recipeService.getRecipeById(recipeId);
    }

    // Define an endpoint to retrieve a recipe by its name.
    @GetMapping("/byName/{name}")
    public List<Recipe> getRecipeByName(@PathVariable String name) {
        return recipeService.findRecipesByName(name);
    }

    // Define an endpoint to retrieve all recipes.
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // Define an endpoint to update an existing recipe.
    @PutMapping("/{recipeId}")
    public Recipe updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe recipe) {
        // Check if the existing recipe with the given ID exists.
        return recipeService.updateRecipe(recipeId, recipe);

    }

    // Define an endpoint to delete a recipe by its ID.
    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}
