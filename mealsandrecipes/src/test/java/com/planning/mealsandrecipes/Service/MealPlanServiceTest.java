package com.planning.mealsandrecipes.Service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.model.MealModel;
import com.planning.mealsandrecipes.model.NutritionModel;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import com.planning.mealsandrecipes.service.MealPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealPlanServiceTest {

    @Mock
    private RecipeRepo recipeRepository;

    @Mock
    private IngredientRepo ingredientRepo;

    @Mock
    private RecipeIngredientRepo recipeIngredientRepo;

    @InjectMocks
    private MealPlanService mealPlanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getMeal() {
        // Arrange
        String mealType = "Breakfast";
        String recipeType = "Healthy";
        Recipe recipe = new Recipe();
        when(recipeRepository.findByMealTypeAndRecipeType(mealType, recipeType)).thenReturn(Collections.singletonList(recipe));

        // Act
        List<MealModel> mealModels = mealPlanService.getMeal(mealType, recipeType);

        // Assert
        assertEquals(1, mealModels.size());
        verify(recipeRepository, times(1)).findByMealTypeAndRecipeType(mealType, recipeType);
    }

    @Test
    void getMealClientSpecific_NoDietRestrictions_NoCalorieLimit() {
        // Arrange
        String mealType = "Main Course";
        String recipeType = "Vegetarian";
        int clientId = 2;
        String clientName = "test";
        List<String> dietRestrictions = null;
        int calorieLimit = Integer.MAX_VALUE;

        Recipe recipe = new Recipe();
        recipe.setRecipeId(1);
        recipe.setClient(clientId);
        recipe.setRecipeName("Chicken Salad");

        RecipeIngredient recipeIngredient1 = new RecipeIngredient();
        recipeIngredient1.setIngredient_id(1L);
        recipeIngredient1.setQuantity("200g");

        RecipeIngredient recipeIngredient2 = new RecipeIngredient();
        recipeIngredient2.setIngredient_id(2L);
        recipeIngredient2.setQuantity("1 unit");

        List<Recipe> recipes = Collections.singletonList(recipe);
        when(recipeRepository.findByMealTypeAndRecipeTypeAndClient(mealType, recipeType, clientId)).thenReturn(recipes);
        when(recipeIngredientRepo.findByRecipeId(recipe.getRecipeId())).thenReturn(Arrays.asList(recipeIngredient1, recipeIngredient2));

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setCalories(100.0);
        ingredient1.setFat(5.0);
        ingredient1.setCarbohydrates(10.0);
        ingredient1.setFiber(2.0);
        ingredient1.setSugar(1.0);
        ingredient1.setProtein(15.0);
        ingredient1.setSodium(500.0);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setCalories(50.0);
        ingredient2.setFat(2.0);
        ingredient2.setCarbohydrates(5.0);
        ingredient2.setFiber(1.0);
        ingredient2.setSugar(0.0);
        ingredient2.setProtein(8.0);
        ingredient2.setSodium(200.0);

        when(ingredientRepo.findById(1L)).thenReturn(java.util.Optional.of(ingredient1));
        when(ingredientRepo.findById(2L)).thenReturn(java.util.Optional.of(ingredient2));

        // Act
        List<MealModel> mealModels = mealPlanService.getMealClientSpecific(mealType, recipeType, clientId, clientName, dietRestrictions, calorieLimit);

        // Assert
        assertEquals(recipe, mealModels.get(0).getRecipe()); // Check if the retrieved recipe is in the result
        System.out.println(mealModels.size());
        assertEquals(1, mealModels.size());

    }

    @Test
    void getMealClientSpecific_WithDietRestrictions_And_CalorieLimit() {
        // Arrange
        String mealType = "Main Course";
        String recipeType = "Vegetarian";
        int clientId = 2;
        String clientName = "test";
        List<String> dietRestrictions = Collections.singletonList("tomato");
        int calorieLimit = Integer.MAX_VALUE;

        Recipe recipe = new Recipe();
        recipe.setRecipeId(1);
        recipe.setClient(clientId);
        recipe.setRecipeName("Chicken Salad");

        RecipeIngredient recipeIngredient1 = new RecipeIngredient();
        recipeIngredient1.setIngredient_id(1L);
        recipeIngredient1.setQuantity("200g tomato");

        RecipeIngredient recipeIngredient2 = new RecipeIngredient();
        recipeIngredient2.setIngredient_id(2L);
        recipeIngredient2.setQuantity("200g onion");

        List<Recipe> recipes = Collections.singletonList(recipe);
        when(recipeRepository.findByMealTypeAndRecipeTypeAndClient(mealType, recipeType, clientId)).thenReturn(recipes);
        when(recipeIngredientRepo.findByRecipeId(recipe.getRecipeId())).thenReturn(Arrays.asList(recipeIngredient1, recipeIngredient2));

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("tomato");
        ingredient1.setCalories(100.0);
        ingredient1.setFat(5.0);
        ingredient1.setCarbohydrates(10.0);
        ingredient1.setFiber(2.0);
        ingredient1.setSugar(1.0);
        ingredient1.setProtein(15.0);
        ingredient1.setSodium(500.0);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("onion");
        ingredient2.setId(2L);
        ingredient2.setCalories(50.0);
        ingredient2.setFat(2.0);
        ingredient2.setCarbohydrates(5.0);
        ingredient2.setFiber(1.0);
        ingredient2.setSugar(0.0);
        ingredient2.setProtein(8.0);
        ingredient2.setSodium(200.0);

        when(ingredientRepo.findById(1L)).thenReturn(java.util.Optional.of(ingredient1));
        when(ingredientRepo.findById(2L)).thenReturn(java.util.Optional.of(ingredient2));

        // Act
        List<MealModel> mealModels = mealPlanService.getMealClientSpecific(mealType, recipeType, clientId, clientName, dietRestrictions, calorieLimit);

        // Assert
        System.out.println(mealModels.size());
        assertEquals(0, mealModels.size());


    }

    @Test
    void findIngredients() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setRecipeId(1);
        recipe.setRecipeName("Chicken Salad");

        RecipeIngredient recipeIngredient1 = new RecipeIngredient();
        recipeIngredient1.setIngredient_id(1L);
        recipeIngredient1.setQuantity("200g");

        RecipeIngredient recipeIngredient2 = new RecipeIngredient();
        recipeIngredient2.setIngredient_id(2L);
        recipeIngredient2.setQuantity("1 unit");

        List<Recipe> recipes = Collections.singletonList(recipe);
        when(recipeIngredientRepo.findByRecipeId(recipe.getRecipeId())).thenReturn(Arrays.asList(recipeIngredient1, recipeIngredient2));

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setCalories(100.0);
        ingredient1.setFat(5.0);
        ingredient1.setCarbohydrates(10.0);
        ingredient1.setFiber(2.0);
        ingredient1.setSugar(1.0);
        ingredient1.setProtein(15.0);
        ingredient1.setSodium(500.0);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setCalories(50.0);
        ingredient2.setFat(2.0);
        ingredient2.setCarbohydrates(5.0);
        ingredient2.setFiber(1.0);
        ingredient2.setSugar(0.0);
        ingredient2.setProtein(8.0);
        ingredient2.setSodium(200.0);

        when(ingredientRepo.findById(1L)).thenReturn(java.util.Optional.of(ingredient1));
        when(ingredientRepo.findById(2L)).thenReturn(java.util.Optional.of(ingredient2));

        // Act
        List<MealModel> mealModels = mealPlanService.findIngredients(recipes);

        // Assert
        assertEquals(1, mealModels.size());
        MealModel mealModel = mealModels.get(0);

        // Check recipe details
        assertEquals(recipe, mealModel.getRecipe());

        // Check ingredient list
        List<String> expectedIngredientsList = Arrays.asList("200g", "1 unit");
        assertEquals(expectedIngredientsList, mealModel.getIngredientList());

        // Check nutrition model
        NutritionModel nutritionModel = mealModel.getNutritionModel();
        assertNotNull(nutritionModel);
        assertEquals(250.0, nutritionModel.getCalories()); // 100 * 200/100 + 50 * 1
        assertEquals(12.0, nutritionModel.getFat()); // 5 * 200/100 + 2 * 1
        assertEquals(25.0, nutritionModel.getCarbohydrates()); // 10 * 200/100 + 5 * 1
        assertEquals(5.0, nutritionModel.getFiber()); // 2 * 200/100 + 1 * 1
        assertEquals(2.0, nutritionModel.getSugar()); // 1 * 200/100 + 0 * 1
        assertEquals(38.0, nutritionModel.getProtein()); // 15 * 200/100 + 8 * 1
        assertEquals(1200.0, nutritionModel.getSodium()); // 500 * 200/100 + 200 * 1
    }

    @Test
    void containsIngredient() {
        // Arrange
        List<String> ingredients = Arrays.asList("Chicken Breast", "Broccoli", "Olive Oil");
        List<String> dietRestrictions = Arrays.asList("gluten", "sugar");

        // Act
        boolean containsRestrictedIngredient = MealPlanService.containsIngredient(ingredients, dietRestrictions);

        // Assert
        assertFalse(containsRestrictedIngredient, "No restricted ingredients should be found");
    }

    @Test
    void containsIngredient_WithRestrictedIngredient() {
        // Arrange
        List<String> ingredients = Arrays.asList("Chicken Breast", "Whole Wheat Pasta", "Olive Oil");
        List<String> dietRestrictions = Arrays.asList("Wheat Pasta", "sugar");

        // Act
        boolean containsRestrictedIngredient = MealPlanService.containsIngredient(ingredients, dietRestrictions);

        // Assert
        assertTrue(containsRestrictedIngredient, "Restricted ingredient should be found");
    }

    @Test
    void containsIngredient_CaseInsensitive() {
        // Arrange
        List<String> ingredients = Arrays.asList("Chicken Breast", "Brown Rice", "Olive Oil");
        List<String> dietRestrictions = Arrays.asList("brown", "olive");

        // Act
        boolean containsRestrictedIngredient = MealPlanService.containsIngredient(ingredients, dietRestrictions);

        // Assert
        assertTrue(containsRestrictedIngredient, "Restricted ingredient should be found (case-insensitive)");
    }
}

