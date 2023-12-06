package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepo ingredientRepository;

    // Save an ingredient to the repository.
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // Retrieve an ingredient by its ID.
    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public List<Ingredient> getByName(String name) {
        return ingredientRepository.findByNameContainingIgnoreCase(name);
    }

    // Retrieve a list of all ingredients.
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    // Delete an ingredient by its ID.
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> saveAll(List<Ingredient> ingredientsList) {
        return ingredientRepository.saveAll(ingredientsList);
    }



}
