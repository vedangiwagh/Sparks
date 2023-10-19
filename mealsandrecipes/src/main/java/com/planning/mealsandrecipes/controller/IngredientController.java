package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
public class IngredientController {

    private IngredientService ingredientService;

//    @PostMapping
//    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
//        return ingredientService.create(ingredient);
//    }

    @GetMapping("/{id}")
    public Optional<Ingredient> getIngredient(@PathVariable Long id) {
        return ingredientService.get((long)1);
    }
//
//    @PutMapping("/{id}")
//    public Ingredient updateIngredient(@PathVariable Long id, @RequestBody Ingredient updatedIngredient) {
//        return ingredientService.update(id, updatedIngredient);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteIngredient(@PathVariable Long id) {
//        ingredientService.delete(id);
//    }
}
