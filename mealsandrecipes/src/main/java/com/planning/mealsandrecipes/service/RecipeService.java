import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Create a new recipe and save it to the repository.
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // Retrieve a recipe by its ID.
    public Recipe getRecipeById(Integer recipeId) {
        return recipeRepository.findById(recipeId).orElse(null);
    }

    // Retrieve a list of all recipes.
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Update an existing recipe with the provided data.
    public void updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    // Delete a recipe by its ID.
    public void deleteRecipe(Integer recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
