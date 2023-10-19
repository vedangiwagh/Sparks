import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void createRecipe_shouldSaveRecipe() {
        Recipe recipe = new Recipe();
        when(recipeRepository.save(any())).thenReturn(recipe);

        Recipe savedRecipe = recipeService.createRecipe(recipe);

        verify(recipeRepository).save(recipe);
        assertEquals(recipe, savedRecipe);
    }

    @Test
    void getRecipeById_shouldReturnRecipeIfExists() {
        int recipeId = 1;
        Recipe recipe = new Recipe();
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

        Recipe retrievedRecipe = recipeService.getRecipeById(recipeId);

        verify(recipeRepository).findById(recipeId);
        assertEquals(recipe, retrievedRecipe);
    }

    @Test
    void getRecipeById_shouldReturnNullIfNotExists() {
        int recipeId = 1;
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());

        Recipe retrievedRecipe = recipeService.getRecipeById(recipeId);

        verify(recipeRepository).findById(recipeId);
        assertNull(retrievedRecipe);
    }

    @Test
    void getAllRecipes_shouldReturnListOfRecipes() {
        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> allRecipes = recipeService.getAllRecipes();

        verify(recipeRepository).findAll();
        assertEquals(recipes, allRecipes);
    }

    @Test
    void updateRecipe_shouldSaveRecipe() {
        Recipe recipe = new Recipe();

        recipeService.updateRecipe(recipe);

        verify(recipeRepository).save(recipe);
    }

    @Test
    void deleteRecipe_shouldDeleteRecipe() {
        int recipeId = 1;

        recipeService.deleteRecipe(recipeId);

        verify(recipeRepository).deleteById(recipeId);
    }
}
