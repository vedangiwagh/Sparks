import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Define an endpoint to create a new recipe.
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    // Define an endpoint to retrieve a recipe by its ID.
    @GetMapping("/{recipeId}")
    public Recipe getRecipe(@PathVariable Integer recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.NOT_FOUND).getBody();
        }
    }

    // Define an endpoint to retrieve all recipes.
    @GetMapping
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        if (recipes != null) {
            return new ResponseEntity<>(recipes, HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<>(recipes, HttpStatus.NOT_FOUND).getBody();
        }
    }

    // Define an endpoint to update an existing recipe.
    @PutMapping("/{recipeId}")
    public void updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe recipe) {
        // Check if the existing recipe with the given ID exists.
        Recipe existingRecipe = recipeService.getRecipeById(recipeId);
        if (existingRecipe != null) {
            // If it exists, update the recipe with the provided data.
            recipeService.updateRecipe(recipe);
        }
    }

    // Define an endpoint to delete a recipe by its ID.
    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}
