package com.planning.mealsandrecipes.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import com.planning.mealsandrecipes.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class IngredientServiceTest {

    @InjectMocks
    private IngredientService ingredientService;

    @Mock
    private IngredientRepo ingredientRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveIngredient() {
        Ingredient ingredient = new Ingredient();
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);

        Ingredient savedIngredient = ingredientService.save(ingredient);

        assertEquals(ingredient, savedIngredient);
    }

    @Test
    public void testGetIngredientById() {
        Long id = 1L;
        Ingredient ingredient = new Ingredient();
        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));

        Ingredient retrievedIngredient = ingredientService.getById(id);

        assertEquals(ingredient, retrievedIngredient);
    }

    @Test
    public void testGetIngredientByIdNotFound() {
        Long id = 1L;
        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());

        Ingredient retrievedIngredient = ingredientService.getById(id);

        assertNull(retrievedIngredient);
    }

    @Test
    public void testGetAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        when(ingredientRepository.findAll()).thenReturn(ingredients);

        List<Ingredient> allIngredients = ingredientService.getAll();

        assertEquals(ingredients, allIngredients);
    }

    @Test
    public void testDeleteIngredient() {
        Long id = 1L;
        doNothing().when(ingredientRepository).deleteById(id);

        ingredientService.delete(id);

        verify(ingredientRepository, times(1)).deleteById(id);
    }

    @Test
    public void testSaveAllIngredients() {
        List<Ingredient> ingredientsList = new ArrayList<>();
        when(ingredientRepository.saveAll(ingredientsList)).thenReturn(ingredientsList);

        List<Ingredient> savedIngredients = ingredientService.saveAll(ingredientsList);

        assertEquals(ingredientsList, savedIngredients);
    }
}
