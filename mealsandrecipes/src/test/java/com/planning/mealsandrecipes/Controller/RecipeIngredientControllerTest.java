package com.planning.mealsandrecipes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planning.mealsandrecipes.controller.RecipeIngredientController;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.service.RecipeIngredientService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeIngredientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeIngredientService recipeIngredientService;

    @InjectMocks
    private RecipeIngredientController recipeIngredientController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeIngredientController).build();
    }

    @Test
    public void testCreateRecipeIngredient() throws Exception {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setId(1);
        recipeIngredient.setRecipeId(1);
        recipeIngredient.setIngredient_id(123L);
        recipeIngredient.setQuantity("200g");

        when(recipeIngredientService.save(any(RecipeIngredient.class))).thenReturn(recipeIngredient);

        mockMvc.perform(post("/recipe-ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeIngredient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.recipeId").value(1))
                .andExpect(jsonPath("$.ingredient_id").value(123))
                .andExpect(jsonPath("$.quantity").value("200g"));

        verify(recipeIngredientService, times(1)).save(any(RecipeIngredient.class));
        verifyNoMoreInteractions(recipeIngredientService);
    }

    @Test
    public void testCreateRecipeIngredientsBulk() throws Exception {
        RecipeIngredient ingredient1 = new RecipeIngredient();
        ingredient1.setId(1);
        ingredient1.setRecipeId(1);
        ingredient1.setIngredient_id(123L);
        ingredient1.setQuantity("200g");

        RecipeIngredient ingredient2 = new RecipeIngredient();
        ingredient2.setId(2);
        ingredient2.setRecipeId(2);
        ingredient2.setIngredient_id(456L);
        ingredient2.setQuantity("300g");

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(ingredient1);
        recipeIngredients.add(ingredient2);

        when(recipeIngredientService.saveAll(anyList())).thenReturn(recipeIngredients);

        mockMvc.perform(post("/recipe-ingredients/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeIngredients)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].recipeId").value(1))
                .andExpect(jsonPath("$[0].ingredient_id").value(123))
                .andExpect(jsonPath("$[0].quantity").value("200g"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].recipeId").value(2))
                .andExpect(jsonPath("$[1].ingredient_id").value(456))
                .andExpect(jsonPath("$[1].quantity").value("300g"));

        verify(recipeIngredientService, times(1)).saveAll(anyList());
        verifyNoMoreInteractions(recipeIngredientService);
    }

    // Helper method to convert objects to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
