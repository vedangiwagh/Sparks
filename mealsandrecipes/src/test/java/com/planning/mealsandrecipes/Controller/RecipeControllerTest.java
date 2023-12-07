package com.planning.mealsandrecipes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planning.mealsandrecipes.controller.RecipeController;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.model.MealModel;
import com.planning.mealsandrecipes.model.NutritionModel;
import com.planning.mealsandrecipes.model.RecipeRequest;
import com.planning.mealsandrecipes.service.RecipeService;
import com.planning.mealsandrecipes.service.MealPlanService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

public class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private MealPlanService mealPlanService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

//    @Test
//    void createRecipe() throws Exception {
//        // Arrange
//        Recipe recipe = new Recipe();
//        recipe.setRecipeName("Spaghetti");
//        when(recipeService.createRecipe(any())).thenReturn(recipe.getRecipeId());
//
//        // Act & Assert
//        mockMvc.perform(MockMvcRequestBuilders.post("/recipes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(recipe)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.recipeName").value(recipe.getRecipeName()))
//                .andDo(print());
//
//        // Verify
//        verify(recipeService, times(1)).createRecipe(any());
//    }


    @Test
    public void testGetRecipeByName() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        when(recipeService.findRecipesByName("TestRecipe")).thenReturn(recipes);

        mockMvc.perform(get("/recipes/byName/TestRecipe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(recipeService, times(1)).findRecipesByName("TestRecipe");
        verifyNoMoreInteractions(recipeService);
    }

    @Test
    public void testGetAllRecipes() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(recipeService, times(1)).getAllRecipes();
        verifyNoMoreInteractions(recipeService);
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isOk());

        verify(recipeService, times(1)).deleteRecipe(1);
        verifyNoMoreInteractions(recipeService);
    }

    @Test
    void getRecipe() throws Exception {
        // Arrange
        Recipe recipe = new Recipe();
        when(recipeService.getRecipeById(anyInt())).thenReturn(recipe);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.recipeName").value(recipe.getRecipeName()));

        // Verify
        verify(recipeService, times(1)).getRecipeById(anyInt());
    }

    @Test
    void getNutritionRecipe() throws Exception {
        // Arrange
        int recipeId = 1;
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Chicken Curry");

        NutritionModel nutritionModel = new NutritionModel();
        nutritionModel.setCalories(500.0);
        nutritionModel.setFat(20.0);
        nutritionModel.setCarbohydrates(60.0);
        nutritionModel.setProtein(30.0);

        MealModel mealModel = new MealModel();
        mealModel.setRecipe(recipe);
        mealModel.setNutritionModel(nutritionModel);

        List<MealModel> mealModels = new ArrayList<>();
        mealModels.add(mealModel);

        when(recipeService.getRecipeById(recipeId)).thenReturn(recipe);
        when(mealPlanService.findIngredients(anyList())).thenReturn(mealModels);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/nutrition/{recipeId}", recipeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calories").value(nutritionModel.getCalories()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fat").value(nutritionModel.getFat()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carbohydrates").value(nutritionModel.getCarbohydrates()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.protein").value(nutritionModel.getProtein()))
                .andDo(print());

        // Verify
        verify(recipeService, times(1)).getRecipeById(recipeId);
        verify(mealPlanService, times(1)).findIngredients(anyList());
    }

    @Test
    void getAllRecipesByClient() throws Exception {
        // Arrange
        int clientId = 1;
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeName("Pasta");
        Recipe recipe2 = new Recipe();
        recipe2.setRecipeName("Pizza");
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

        when(recipeService.getAllRecipesByClient(clientId)).thenReturn(recipes);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/all/{client}", clientId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipeName").value(recipe1.getRecipeName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].recipeName").value(recipe2.getRecipeName()))
                .andDo(print());

        // Verify
        verify(recipeService, times(1)).getAllRecipesByClient(clientId);
    }

    @Test
    void updateRecipe() throws Exception {
        // Arrange
        int recipeId = 1;
        Recipe existingRecipe = new Recipe();
        existingRecipe.setRecipeName("Spaghetti");

        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setRecipeName("Spaghetti Aglio e Olio");

        when(recipeService.updateRecipe(eq(recipeId), any())).thenReturn(updatedRecipe);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/recipes/{recipeId}", recipeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRecipe)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.recipeName").value(updatedRecipe.getRecipeName()))
                .andDo(print());

        // Verify
        verify(recipeService, times(1)).updateRecipe(eq(recipeId), any());
    }

    @Test
    void getRecipeForClient() throws Exception {
        // Arrange
        RecipeRequest request = new RecipeRequest();
        request.setMealType("Lunch");
        request.setRecipeType("Italian");
        request.setClient(1);

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeName("Pasta");
        Recipe recipe2 = new Recipe();
        recipe2.setRecipeName("Pizza");
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

        when(recipeService.findRecipesforClient(request.getMealType(), request.getRecipeType(), request.getClient()))
                .thenReturn(recipes);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/recipes/getRecipesForClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipeName").value(recipe1.getRecipeName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].recipeName").value(recipe2.getRecipeName()))
                .andDo(print());

        // Verify
        verify(recipeService, times(1)).findRecipesforClient(request.getMealType(), request.getRecipeType(), request.getClient());
    }

    @Test
    void getRecipeByNameAndClient() throws Exception {
        // Arrange
        String recipeName = "Spaghetti";
        int clientId = 1;

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeName(recipeName);

        when(recipeService.findRecipesByNameAndClient(recipeName, clientId)).thenReturn(Arrays.asList(recipe1));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/byNameAndClient/{name}/{client}", recipeName, clientId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipeName").value(recipe1.getRecipeName()))
                .andDo(print());

        // Verify
        verify(recipeService, times(1)).findRecipesByNameAndClient(recipeName, clientId);
    }
}

