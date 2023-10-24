package com.planning.mealsandrecipes.Service;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.service.IngredientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
public class IngredientServiceTest {

    @Autowired
    private IngredientService ingredientService;

    @Test
    public void testSaveAndGetIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Test Ingredient");

        Ingredient savedIngredient = ingredientService.save(ingredient);
        assertNotNull(savedIngredient.getId());

        Long ingredientId = savedIngredient.getId();
        Ingredient retrievedIngredient = ingredientService.getById(ingredientId);
        assertNotNull(retrievedIngredient);
        assertEquals("Test Ingredient", retrievedIngredient.getName());
    }

    @Test
    public void testGetAllIngredients() {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Ingredient 1");
        ingredientService.save(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Ingredient 2");
        ingredientService.save(ingredient2);

        List<Ingredient> ingredients = ingredientService.getAll();
        assertEquals(2, ingredients.size());
    }

    @Test
    public void testDeleteIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Ingredient to Delete");
        Ingredient savedIngredient = ingredientService.save(ingredient);
        Long ingredientId = savedIngredient.getId();

        ingredientService.delete(ingredientId);
        Ingredient retrievedIngredient = ingredientService.getById(ingredientId);
        assertNull(retrievedIngredient);
    }
}
