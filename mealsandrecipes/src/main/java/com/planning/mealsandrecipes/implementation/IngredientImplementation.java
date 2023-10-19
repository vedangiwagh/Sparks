package com.planning.mealsandrecipes.implementation;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import com.planning.mealsandrecipes.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class IngredientImplementation implements IngredientService {
    private IngredientRepo ingredientRepository;

    public Optional<Ingredient> get(Long id) {
        return ingredientRepository.findById(id);
    }

}
