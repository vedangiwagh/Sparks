package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/ingredients")
// Define CrossOrigin to allow requests from specific origins.
// Tag the controller for Swagger documentation.
@Tag(description = "Set of endpoints for ingredients.", name = "Ingredient Controller")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    // Define an endpoint to get a specific ingredient by its ID.
    @Operation(summary = "Returns a specific ingredient based on given parameter.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of ingredients")
    @ApiResponse(responseCode = "404", description = "no ingredients")
    @GetMapping(value = "/{id}")
   public Ingredient getIngredient(@Parameter(description = "ID of the ingredient.", example = "1") @PathVariable Long id) {
        return ingredientService.getById(id);
    }

    // Define an endpoint to get a list of all ingredients in the database.
    @ApiResponse(responseCode = "200", description = "Successful retrieval of ingredients")
    @ApiResponse(responseCode = "404", description = "Ingredient not exist with id")
    @Operation(summary = "Returns a list of all ingredients in the database.")
    @GetMapping()
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAll();
    }


    @PostMapping
    @Operation(summary = "Create a new ingredient")
    @ApiResponse(responseCode = "201", description = "ingredient created successfully")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.save(ingredient);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create a new ingredients in bulk")
    @ApiResponse(responseCode = "201", description = "ingredients created successfully")
    public List<Ingredient> createIngredients(@RequestBody List<Ingredient> ingredients) {
        return ingredientService.saveAll(ingredients);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete ingredient")
    @ApiResponse(responseCode = "200", description = "ingredient deleted successfully")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.delete(id);
    }

    // Define exception handling for NoSuchElementException.
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    // Define a more general exception handler to handle various exceptions.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        String message;

        if (e instanceof HttpMessageNotReadableException) {
            message = "Error while parsing JSON. Please enter valid inputs.";
        }
        else if (e instanceof MethodArgumentTypeMismatchException) {
            message = "Wrong argument type. Please try again.";
        }
        else if (e instanceof NumberFormatException) {
            message = "Please enter a valid number.";
        }
        else if (e instanceof HttpRequestMethodNotSupportedException) {
            message = "Wrong request method. Please try again.";
        }
        else {
            message = e.getMessage();
        }
        return ResponseEntity
                .badRequest()
                .body((message));
    }
}
