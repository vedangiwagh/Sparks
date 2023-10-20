package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Ingredient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/ingredients")
// Define CrossOrigin to allow requests from specific origins.
@CrossOrigin(
        origins = {"http://localhost:8000"},
        maxAge = 3800,
        allowCredentials = "true"
)
@AllArgsConstructor
// Tag the controller for Swagger documentation.
@Tag(description = "Set of endpoints for ingredients.", name = "Ingredient Controller")
public class IngredientController {

    private final IngredientService ingredientService;

    // Define an endpoint to get a specific ingredient by its ID.
    @GetMapping(value = "/{id}")
    @Operation(summary = "Returns a specific ingredient based on given parameter.")
    public Ingredient getIngredient(@Parameter(description = "ID of the ingredient.", example = "1") @PathVariable Long id) {

        Ingredient ingredient = ingredientService.get(id);
        if (ingredient != null) {
            return new ResponseEntity<>(ingredient, HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<>(ingredient, HttpStatus.NOT_FOUND).getBody();
        }
    }

    // Define an endpoint to get a list of all ingredients in the database.
    @GetMapping()
    @Operation(summary = "Returns a list of all ingredients in the database.")
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAll();
        if (ingredients != null) {
            return new ResponseEntity<>(ingredient, HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<>(ingredient, HttpStatus.NOT_FOUND).getBody();
        }
    }

    // Define exception handling for NoSuchElementException.
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(e.getMessage()));
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
                .body(new MessageResponse(message));
    }
}
