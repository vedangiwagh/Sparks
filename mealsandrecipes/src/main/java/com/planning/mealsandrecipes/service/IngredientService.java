package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IngredientService {

    public Optional<Ingredient> get(Long id);
}
