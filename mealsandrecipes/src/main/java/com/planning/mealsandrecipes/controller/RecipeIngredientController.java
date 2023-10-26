package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.service.RecipeIngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe-ingredients")
public class RecipeIngredientController {
    private final RecipeIngredientService recipeIngredientService;

    @Autowired
    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @PostMapping
    @Operation(summary = "Create a new RecipeIngredient mapping")
    @ApiResponse(responseCode = "201", description = "RecipeIngredient created successfully")
    public RecipeIngredient createRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        // Implement logic to create a recipe ingredient
        return recipeIngredientService.save(recipeIngredient);
    }


    @GetMapping
    @Operation(summary = "Returns all RecipeIngredient")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of RecipeIngredient")
    @ApiResponse(responseCode = "404", description = "no RecipeIngredient")
    public List<RecipeIngredient> getAllRecipeIngredients() {
        // Implement logic to retrieve all recipe ingredients
        return recipeIngredientService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a specific RecipeIngredient based on recipe id.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of RecipeIngredient")
    @ApiResponse(responseCode = "404", description = "no RecipeIngredient")
    public List<RecipeIngredient> getAllRecipeIngredientsByRecipeId(@PathVariable Integer id) {
        // Implement logic to retrieve all recipe ingredients
        return recipeIngredientService.findRecipesById(id);
    }
    @Operation(summary = "Create a new RecipeIngredient in bulk")
    @ApiResponse(responseCode = "201", description = "RecipeIngredient created successfully")
    @PostMapping("/bulk")
    public List<RecipeIngredient> createRecipeIngredients(@RequestBody List<RecipeIngredient> recipeIngredients) {
        return recipeIngredientService.saveAll(recipeIngredients);
    }

    //delete
}