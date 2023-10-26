package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    // Define an endpoint to create a new recipe.

    @Operation(summary = "Create a new Recipe")
    @ApiResponse(responseCode = "201", description = "Recipe created successfully")
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    // Define an endpoint to retrieve a recipe by its ID.
    @Operation(summary = "Returns a specific Recipe based on recipe id.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of Recipes")
    @ApiResponse(responseCode = "404", description = "no Recipes")
    @GetMapping("/{recipeId}")
    public Recipe getRecipe(@PathVariable Integer recipeId) {
        return recipeService.getRecipeById(recipeId);
    }

    // Define an endpoint to retrieve a recipe by its name.
    @Operation(summary = "Returns a specific Recipe based on name.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of Recipe")
    @ApiResponse(responseCode = "404", description = "no Recipes")
    @GetMapping("/byName/{name}")
    public List<Recipe> getRecipeByName(@PathVariable String name) {
        return recipeService.findRecipesByName(name);
    }

    // Define an endpoint to retrieve all recipes.
    @Operation(summary = "Returns all Recipes")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of Recipes")
    @ApiResponse(responseCode = "404", description = "no Recipes")
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // Define an endpoint to update an existing recipe.
    @PutMapping("/{recipeId}")
    @Operation(summary = "Create a new recipe")
    @ApiResponse(responseCode = "201", description = "Recipe created successfully")
    public Recipe updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe recipe) {
        // Check if the existing recipe with the given ID exists.
        return recipeService.updateRecipe(recipeId, recipe);

    }

    // Define an endpoint to delete a recipe by its ID.
    @DeleteMapping("/{recipeId}")
    @Operation(summary = "Delete Recipe")
    @ApiResponse(responseCode = "200", description = "Recipe deleted successfully")
    public void deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}
