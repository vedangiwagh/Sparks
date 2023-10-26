package com.planning.mealsandrecipes.Controller;

import com.planning.mealsandrecipes.controller.RecipeController;
import com.planning.mealsandrecipes.entity.Recipe;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

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
}

