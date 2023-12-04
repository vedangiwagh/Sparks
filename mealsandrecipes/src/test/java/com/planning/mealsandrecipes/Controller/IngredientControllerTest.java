package com.planning.mealsandrecipes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planning.mealsandrecipes.controller.IngredientController;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testGetIngredient() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Test Ingredient");

        when(ingredientService.getById(1L)).thenReturn(ingredient);

        mockMvc.perform(get("/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Ingredient"));
    }

    @Test
    public void testGetAllIngredients() throws Exception {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Ingredient 1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Ingredient 2");

        List<Ingredient> ingredientList = Arrays.asList(ingredient1, ingredient2);

        when(ingredientService.getAll()).thenReturn(ingredientList);

        mockMvc.perform(get("/ingredients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Ingredient 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Ingredient 2"));
    }

    @Test
    public void testCreateIngredient() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Test Ingredient");

        when(ingredientService.save(any(Ingredient.class))).thenReturn(ingredient);

        mockMvc.perform(post("/ingredients")
                .contentType("application/json")
                .content(asJsonString(ingredient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Ingredient"));
    }

    @Test
    public void testCreateIngredientsBulk() throws Exception {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Ingredient 1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Ingredient 2");

        List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2);

        when(ingredientService.saveAll(any(List.class))).thenReturn(ingredients);

        mockMvc.perform(post("/ingredients/bulk")
                .contentType("application/json")
                .content(asJsonString(ingredients)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Ingredient 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Ingredient 2"));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        mockMvc.perform(delete("/ingredients/1"))
                .andExpect(status().isOk());
    }

    // Method to convert objects to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
