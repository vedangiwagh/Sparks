package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.service.RecipeIngredientService;
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
    public RecipeIngredient createRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        // Implement logic to create a recipe ingredient
        return recipeIngredientService.save(recipeIngredient);
    }


    @GetMapping
    public List<RecipeIngredient> getAllRecipeIngredients() {
        // Implement logic to retrieve all recipe ingredients
        return recipeIngredientService.getAll();
    }

    @GetMapping("/{id}")
    public List<RecipeIngredient> getAllRecipeIngredientsByRecipeId(@PathVariable Integer id) {
        // Implement logic to retrieve all recipe ingredients
        return recipeIngredientService.getById(id);
    }
    @PostMapping("/bulk")
    public List<RecipeIngredient> createRecipeIngredients(@RequestBody List<RecipeIngredient> recipeIngredients) {
        return recipeIngredientService.saveAll(recipeIngredients);
    }

    //delete
}