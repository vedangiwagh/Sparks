package com.planning.mealsandrecipes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

// Import the Ingredient class
import com.planning.mealsandrecipes.model.Ingredient;

public interface IngredientService {

    Ingredient get(Long id);

    List<Ingredient> getAll();
}

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient get(Long id) {
        return ingredientRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Ingredient with id: '" + id + "' was not found.")
        );
    }

    @Override
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }
}
