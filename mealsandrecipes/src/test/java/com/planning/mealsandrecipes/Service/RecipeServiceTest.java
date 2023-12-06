package com.planning.mealsandrecipes.Service;

import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import com.planning.mealsandrecipes.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    @Mock
    private RecipeRepo recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createRecipe() {
        // Arrange
        Recipe recipeToSave = new Recipe();
        when(recipeRepository.save(recipeToSave)).thenReturn(recipeToSave);

        // Act
        Recipe savedRecipe = recipeService.createRecipe(recipeToSave);

        // Assert
        assertNotNull(savedRecipe);
        verify(recipeRepository, times(1)).save(recipeToSave);
    }

    @Test
    void getRecipeById_ExistingId_ReturnsRecipe() {
        // Arrange
        long recipeId = 1;
        Recipe expectedRecipe = new Recipe();
        expectedRecipe.setRecipeId(1);
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(expectedRecipe));
        when(recipeRepository.count()).thenReturn(1L);
        // Act
        Recipe recipe = recipeService.getRecipeById((int) recipeId);

        // Assert
        assertNotNull(recipe);
        assertEquals(expectedRecipe, recipe);
        verify(recipeRepository, times(1)).findById(recipeId);
    }

    @Test
    void getRecipeById_NonExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        int recipeId = 1;
        when(recipeRepository.count()).thenReturn(0L);
        when(recipeRepository.findById((long) recipeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> recipeService.getRecipeById(recipeId));
        verify(recipeRepository, times(0)).findById((long) recipeId);
    }


    @Test
    void getAllRecipes() {
        // Arrange
        when(recipeRepository.count()).thenReturn(1L);
        when(recipeRepository.findAll()).thenReturn(Arrays.asList(new Recipe(), new Recipe()));

        // Act
        List<Recipe> recipes = recipeService.getAllRecipes();

        // Assert
        assertEquals(2, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getAllRecipesByClient() {
        // Arrange
        int clientId = 1;
        when(recipeRepository.count()).thenReturn(1L);
        when(recipeRepository.findAllRecipesByClient(clientId)).thenReturn(Arrays.asList(new Recipe(), new Recipe()));

        // Act
        List<Recipe> recipes = recipeService.getAllRecipesByClient(clientId);

        // Assert
        assertEquals(2, recipes.size());
        verify(recipeRepository, times(1)).findAllRecipesByClient(clientId);
    }

    @Test
    void findRecipesByNameAndClient() {
        // Arrange
        String recipeName = "Test Recipe";
        int clientId = 1;
        when(recipeRepository.findByRecipeNameContainingIgnoreCaseAndClient(recipeName, clientId)).thenReturn(Arrays.asList(new Recipe(), new Recipe()));

        // Act
        List<Recipe> recipes = recipeService.findRecipesByNameAndClient(recipeName, clientId);

        // Assert
        assertEquals(2, recipes.size());
        verify(recipeRepository, times(1)).findByRecipeNameContainingIgnoreCaseAndClient(recipeName, clientId);
    }

    @Test
    void updateRecipe_ExistingId_ReturnsUpdatedRecipe() {
        // Arrange
        int recipeId = 1;
        Recipe existingRecipe = new Recipe();
        Recipe updatedRecipeDetails = new Recipe();
        when(recipeRepository.findById((long) recipeId)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepository.save(existingRecipe)).thenReturn(updatedRecipeDetails);

        // Act
        Recipe updatedRecipe = recipeService.updateRecipe(recipeId, updatedRecipeDetails);

        // Assert
        assertNotNull(updatedRecipe);
        assertEquals(updatedRecipeDetails, updatedRecipe);
        verify(recipeRepository, times(1)).findById((long) recipeId);
        verify(recipeRepository, times(1)).save(existingRecipe);
    }

    @Test
    void updateRecipe_NonExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        int recipeId = 1;
        Recipe updatedRecipeDetails = new Recipe();
        when(recipeRepository.findById((long) recipeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> recipeService.updateRecipe(recipeId, updatedRecipeDetails));
        verify(recipeRepository, times(1)).findById((long) recipeId);
    }

    @Test
    void findRecipesByName() {
        // Arrange
        String recipeName = "Test Recipe";
        when(recipeRepository.findByRecipeNameContainingIgnoreCase(recipeName)).thenReturn(Arrays.asList(new Recipe(), new Recipe()));

        // Act
        List<Recipe> recipes = recipeService.findRecipesByName(recipeName);

        // Assert
        assertEquals(2, recipes.size());
        verify(recipeRepository, times(1)).findByRecipeNameContainingIgnoreCase(recipeName);
    }

    @Test
    void findRecipesforClient() {
        // Arrange
        String mealType = "Breakfast";
        String recipeType = "Healthy";
        int clientId = 1;
        when(recipeRepository.findByMealTypeAndRecipeTypeAndClient(mealType, recipeType, clientId)).thenReturn(Arrays.asList(new Recipe(), new Recipe()));

        // Act
        List<Recipe> recipes = recipeService.findRecipesforClient(mealType, recipeType, clientId);

        // Assert
        assertEquals(2, recipes.size());
        verify(recipeRepository, times(1)).findByMealTypeAndRecipeTypeAndClient(mealType, recipeType, clientId);
    }

    @Test
    void deleteRecipe() {
        // Arrange
        int recipeId = 1;

        // Act
        recipeService.deleteRecipe(recipeId);

        // Assert
        verify(recipeRepository, times(1)).deleteById((long) recipeId);
    }

    @Test
    void isDatabaseEmpty() {
        // Arrange
        when(recipeRepository.count()).thenReturn(0L);

        // Act
        boolean isEmpty = recipeService.isDatabaseEmpty();

        // Assert
        assertTrue(isEmpty);
        verify(recipeRepository, times(1)).count();
    }
}
