import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createRecipe_shouldReturnCreatedRecipe() throws Exception {
        Recipe recipe = new Recipe();
        when(recipeService.createRecipe(any())).thenReturn(recipe);

        mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipe)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist()); // Adjust this based on your actual response

        verify(recipeService).createRecipe(any());
    }

    @Test
    void getRecipe_shouldReturnRecipeIfExists() throws Exception {
        int recipeId = 1;
        Recipe recipe = new Recipe();
        when(recipeService.getRecipeById(recipeId)).thenReturn(recipe);

        mockMvc.perform(get("/recipes/{recipeId}", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist()); // Adjust this based on your actual response

        verify(recipeService).getRecipeById(recipeId);
    }

    @Test
    void getAllRecipes_shouldReturnListOfRecipes() throws Exception {
        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").doesNotExist()); // Adjust this based on your actual response

        verify(recipeService).getAllRecipes();
    }

    @Test
    void updateRecipe_shouldUpdateExistingRecipe() throws Exception {
        int recipeId = 1;
        Recipe existingRecipe = new Recipe();
        Recipe updatedRecipe = new Recipe();
        when(recipeService.getRecipeById(recipeId)).thenReturn(existingRecipe);

        mockMvc.perform(put("/recipes/{recipeId}", recipeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRecipe)))
                .andExpect(status().isOk());

        verify(recipeService).updateRecipe(updatedRecipe);
    }

    @Test
    void deleteRecipe_shouldDeleteRecipe() throws Exception {
        int recipeId = 1;

        mockMvc.perform(delete("/recipes/{recipeId}", recipeId))
                .andExpect(status().isOk());

        verify(recipeService).deleteRecipe(recipeId);
    }
}
