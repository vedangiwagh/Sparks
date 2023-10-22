package com.planning.mealsandrecipes.Service;

import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepo recipeRepository;

    @InjectMocks
    private RecipeRepo recipeService;

//    @Test
//    void createRecipe_shouldSaveRecipe() {
//        Recipe recipe = new Recipe();
//        when(recipeRepository.save(any())).thenReturn(recipe);
//
//        Recipe savedRecipe = recipeService.cre(recipe);
//
//        verify(recipeRepository).save(recipe);
//        assertEquals(recipe, savedRecipe);
//    }

    @Test
    void getRecipeById_shouldReturnRecipeIfExists() {
        long recipeId = 1;
        Recipe recipe = new Recipe();
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

        Recipe retrievedRecipe = recipeService.getReferenceById(recipeId);

        verify(recipeRepository).findById(recipeId);
        assertEquals(recipe, retrievedRecipe);
    }

    @Test
    void getRecipeById_shouldReturnNullIfNotExists() {
        long recipeId = 1;
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());

        Recipe retrievedRecipe = recipeService.getReferenceById(recipeId);

        verify(recipeRepository).findById(recipeId);
        assertNull(retrievedRecipe);
    }

    @Test
    void getAllRecipes_shouldReturnListOfRecipes() {
        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> allRecipes = recipeService.findAll();

        verify(recipeRepository).findAll();
        assertEquals(recipes, allRecipes);
    }


    @Test
    void deleteRecipe_shouldDeleteRecipe() {
        long recipeId = 1;

        recipeService.deleteById(recipeId);

        verify(recipeRepository).deleteById(recipeId);
    }
}
