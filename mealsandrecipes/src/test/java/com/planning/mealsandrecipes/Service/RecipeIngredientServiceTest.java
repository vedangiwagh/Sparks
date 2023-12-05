package com.planning.mealsandrecipes.Service;

import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import com.planning.mealsandrecipes.service.RecipeIngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeIngredientServiceTest {

    @Mock
    private RecipeIngredientRepo recipeIngredientRepository;

    @InjectMocks
    private RecipeIngredientService recipeIngredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        // Arrange
        RecipeIngredient recipeIngredientToSave = new RecipeIngredient();
        when(recipeIngredientRepository.save(recipeIngredientToSave)).thenReturn(recipeIngredientToSave);

        // Act
        RecipeIngredient savedRecipeIngredient = recipeIngredientService.save(recipeIngredientToSave);

        // Assert
        assertNotNull(savedRecipeIngredient);
        verify(recipeIngredientRepository, times(1)).save(recipeIngredientToSave);
    }

    @Test
    void findRecipesById() {
        // Arrange
        int recipeId = 1;
        when(recipeIngredientRepository.findByRecipeId(recipeId)).thenReturn(Arrays.asList(new RecipeIngredient(), new RecipeIngredient()));

        // Act
        List<RecipeIngredient> recipeIngredients = recipeIngredientService.findRecipesById(recipeId);

        // Assert
        assertEquals(2, recipeIngredients.size());
        verify(recipeIngredientRepository, times(1)).findByRecipeId(recipeId);
    }

    @Test
    void getAll() {
        // Arrange
        when(recipeIngredientRepository.findAll()).thenReturn(Arrays.asList(new RecipeIngredient(), new RecipeIngredient()));

        // Act
        List<RecipeIngredient> recipeIngredients = recipeIngredientService.getAll();

        // Assert
        assertEquals(2, recipeIngredients.size());
        verify(recipeIngredientRepository, times(1)).findAll();
    }

    @Test
    void saveAll() {
        // Arrange
        List<RecipeIngredient> recipeIngredientsToSave = Arrays.asList(new RecipeIngredient(), new RecipeIngredient());
        when(recipeIngredientRepository.saveAll(recipeIngredientsToSave)).thenReturn(recipeIngredientsToSave);

        // Act
        List<RecipeIngredient> savedRecipeIngredients = recipeIngredientService.saveAll(recipeIngredientsToSave);

        // Assert
        assertEquals(2, savedRecipeIngredients.size());
        verify(recipeIngredientRepository, times(1)).saveAll(recipeIngredientsToSave);
    }
}

