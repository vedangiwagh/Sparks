package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientService {
    private final RecipeIngredientRepo recipeIngredientRepository;

    @Autowired
    public RecipeIngredientService(RecipeIngredientRepo recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public List<RecipeIngredient> getAll() {
        return recipeIngredientRepository.findAll();
    }

    public List<RecipeIngredient> saveAll(List<RecipeIngredient> recipeIngredientsList) {
        return recipeIngredientRepository.saveAll(recipeIngredientsList);
    }
    // Implement service methods for managing recipe ingredients here
    // For example, save, findById, findAll, update, delete, etc.
}