package com.planning.mealsandrecipes.Service;

import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import com.planning.mealsandrecipes.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeServiceTest {

    @Mock
    private RecipeRepo recipeRepo;

    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        recipeService = new RecipeService();
        recipeService.recipeRepository = recipeRepo;
    }

    @Test
    public void testCreateRecipe() {
        Recipe newRecipe = new Recipe();
        when(recipeRepo.save(any(Recipe.class))).thenReturn(newRecipe);

        Recipe createdRecipe = recipeService.createRecipe(newRecipe);
        assertNotNull(createdRecipe);
    }

//    @Test
//    public void testGetRecipeById() {
//        Recipe existingRecipe = new Recipe();
//        existingRecipe.setRecipeId(1);
//
//        when(recipeRepo.findById(1L)).thenReturn(Optional.of(existingRecipe));
//
//        Recipe retrievedRecipe = recipeService.getRecipeById(1);
//        assertNotNull(retrievedRecipe);
//        assertEquals(1, retrievedRecipe.getRecipeId());
//    }

    @Test
    public void testGetRecipeById_ResourceNotFoundException() {
        when(recipeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> recipeService.getRecipeById(1));
    }

//    @Test
//    public void testGetAllRecipes() {
//        List<Recipe> recipeList = new ArrayList<>();
//        recipeList.add(new Recipe());
//        recipeList.add(new Recipe());
//
//        when(recipeRepo.findAll()).thenReturn(recipeList);
//
//        List<Recipe> recipes = recipeService.getAllRecipes();
//        assertEquals(1, recipes.size());
//    }

    @Test
    public void testUpdateRecipe() {
        Recipe existingRecipe = new Recipe();
        existingRecipe.setRecipeId(1);

        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setRecipeName("Updated Recipe");

        when(recipeRepo.findById(1L)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepo.save(any(Recipe.class))).thenReturn(updatedRecipe);

        Recipe result = recipeService.updateRecipe(1, updatedRecipe);
        assertNotNull(result);
        assertEquals("Updated Recipe", result.getRecipeName());
    }

    @Test
    public void testUpdateRecipe_ResourceNotFoundException() {
        when(recipeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            recipeService.updateRecipe(1, new Recipe());
        });
    }

    @Test
    public void testDeleteRecipe() {
        recipeService.deleteRecipe(1);
        // Verify that the deleteById method was called
        Mockito.verify(recipeRepo).deleteById(1L);
    }

    @Test
    public void testIsDatabaseEmpty() {
        when(recipeRepo.count()).thenReturn(0L);
        assertTrue(recipeService.isDatabaseEmpty());

        when(recipeRepo.count()).thenReturn(1L);
        assertFalse(recipeService.isDatabaseEmpty());
    }
}
