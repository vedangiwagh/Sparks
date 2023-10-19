package com.planning.mealsandrecipes.entity;

import com.planning.mealsandrecipes.entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IngredientTests {

    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    public void setUp() {
        ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Salt");
        ingredient1.setCalories(0.0);
        ingredient1.setFat(0.0);
        ingredient1.setCarbohydrates(0.0);
        ingredient1.setFiber(0.0);
        ingredient1.setSugar(0.0);
        ingredient1.setProtein(0.0);
        ingredient1.setSodium(0.0);

        ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Pepper");
        ingredient2.setCalories(0.0);
        ingredient2.setFat(0.0);
        ingredient2.setCarbohydrates(0.0);
        ingredient2.setFiber(0.0);
        ingredient2.setSugar(0.0);
        ingredient2.setProtein(0.0);
        ingredient2.setSodium(0.0);
    }

    @Test
    public void testEquals() {
        assertEquals(ingredient1, ingredient1);
        assertNotEquals(ingredient1, ingredient2);
    }

    @Test
    public void testHashCode() {
        assertEquals(ingredient1.hashCode(), ingredient1.hashCode());
        assertNotEquals(ingredient1.hashCode(), ingredient2.hashCode());
    }

    @Test
    public void testGetName() {
        assertEquals("Salt", ingredient1.getName());
    }

