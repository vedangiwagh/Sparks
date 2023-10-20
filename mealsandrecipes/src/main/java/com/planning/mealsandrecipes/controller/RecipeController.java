import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return recipeService.getRecipeById(recipeId);
    }

    // Define an endpoint to retrieve all recipes.
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
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
