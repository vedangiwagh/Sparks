package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.model.MealModel;
import com.planning.mealsandrecipes.model.NutritionModel;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.model.RecipeRequest;
import com.planning.mealsandrecipes.service.MealPlanService;
import com.planning.mealsandrecipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private MealPlanService mealPlanService;


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

    @Operation(summary = "Returns Recipes specific to params")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of Recipes")
    @ApiResponse(responseCode = "404", description = "no Recipes")
    @PostMapping("/getRecipesForClient")
    public List<Recipe> getRecipeForClient(@RequestBody RecipeRequest request) {
        String mealType = request.getMealType();
        String recipeType = request.getRecipeType();
        int client = request.getClient();

        return recipeService.findRecipesforClient(mealType, recipeType, client);

    }

    @Operation(summary = "Returns a specific Recipe based on name.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of Recipe")
    @ApiResponse(responseCode = "404", description = "no Recipes")
    @GetMapping("/byNameAndClient/{name}/{client}")
    public List<Recipe> getRecipeByNameAndClient(@PathVariable String name, @PathVariable int client) {
        return recipeService.findRecipesByNameAndClient(name,client);
    }

    @Operation(summary = "Returns all Recipes")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of Recipes")
    @ApiResponse(responseCode = "404", description = "no Recipes")
    @GetMapping("/all/{client}")
    public List<Recipe> getAllRecipesByClient(@PathVariable int client) {
        return recipeService.getAllRecipesByClient(client);
    }
    // Define an endpoint to update an existing recipe.
    @PutMapping("/{recipeId}")
    @Operation(summary = "Create a new recipe")
    @ApiResponse(responseCode = "201", description = "Recipe created successfully")
    public Recipe updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe recipe) {
        // Check if the existing recipe with the given ID exists.
        return recipeService.updateRecipe(recipeId, recipe);

    }

    @Operation(summary = "Returns a specific Recipe based nutrition on recipe id.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of nutrition of Recipes")
    @ApiResponse(responseCode = "404", description = "no Recipes")
    @GetMapping("/nutrition/{recipeId}")
    public NutritionModel getNutritionRecipe(@PathVariable Integer recipeId) {

        Recipe recipe = recipeService.getRecipeById(recipeId);
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);
        List<MealModel> mealModels = mealPlanService.findIngredients(recipeList);

        return mealModels.get(0).getNutritionModel();
    }

    // Define an endpoint to delete a recipe by its ID.
    @DeleteMapping("/{recipeId}")
    @Operation(summary = "Delete Recipe")
    @ApiResponse(responseCode = "200", description = "Recipe deleted successfully")
    public void deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}
