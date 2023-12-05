package com.planning.mealsandrecipes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planning.mealsandrecipes.controller.MealPlanController;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.model.MealModel;
import com.planning.mealsandrecipes.model.MealRequest;
import com.planning.mealsandrecipes.service.MealPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class MealPlanControllerTest {

    @Mock
    private MealPlanService mealPlanService;

    @InjectMocks
    private MealPlanController mealPlanController;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mealPlanController).build();
    }

    @Test
    void getMeal() throws Exception {
        // Arrange
        String mealType = "Breakfast";
        String recipeType = "Healthy";

        MealModel mealModel1 = new MealModel();
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(1);
        mealModel1.setRecipe(recipe1);

        MealModel mealModel2 = new MealModel();
        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId(2);
        mealModel2.setRecipe(recipe2);

        List<MealModel> mealModels = Arrays.asList(mealModel1, mealModel2);

        when(mealPlanService.getMeal(mealType, recipeType)).thenReturn(mealModels);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/meals/{mealType}/{recipeType}", mealType, recipeType)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipe.recipeId").value(mealModel1.getRecipe().getRecipeId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].recipe.recipeId").value(mealModel2.getRecipe().getRecipeId()))
                .andDo(print());

        // Verify
        verify(mealPlanService, times(1)).getMeal(mealType, recipeType);
    }

    @Test
    void getMealClientSpecific() throws Exception {
        // Arrange
        MealRequest mealRequest = new MealRequest();
        mealRequest.setMealType("Lunch");
        mealRequest.setRecipeType("Vegetarian");
        mealRequest.setClientId(1);
        mealRequest.setClientName("John");
        ArrayList<String> dietRestrictions = new ArrayList<>();
        dietRestrictions.add("No Nuts");
        dietRestrictions.add("No Dairy");
        mealRequest.setDietRestriction( dietRestrictions);
        mealRequest.setCalorieLimit(500);

        MealModel mealModel1 = new MealModel();
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(1);
        mealModel1.setRecipe(recipe1);

        MealModel mealModel2 = new MealModel();
        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId(2);
        mealModel2.setRecipe(recipe2);

        List<MealModel> mealModels = Arrays.asList(mealModel1, mealModel2);

        when(mealPlanService.getMealClientSpecific(
                mealRequest.getMealType(),
                mealRequest.getRecipeType(),
                mealRequest.getClientId(),
                mealRequest.getClientName(),
                mealRequest.getDietRestriction(),
                mealRequest.getCalorieLimit()
        )).thenReturn(mealModels);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/meals/getMealClientSpecific")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mealRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipe.recipeId").value(mealModel1.getRecipe().getRecipeId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].recipe.recipeId").value(mealModel2.getRecipe().getRecipeId()))
                .andDo(print());

        // Verify
        verify(mealPlanService, times(1)).getMealClientSpecific(
                mealRequest.getMealType(),
                mealRequest.getRecipeType(),
                mealRequest.getClientId(),
                mealRequest.getClientName(),
                mealRequest.getDietRestriction(),
                mealRequest.getCalorieLimit()
        );
    }
}

