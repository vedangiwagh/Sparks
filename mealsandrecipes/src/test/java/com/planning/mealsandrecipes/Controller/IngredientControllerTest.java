import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IngredientService ingredientService;

    @Test
    public void testCreateIngredient() throws Exception {
        // Define the JSON content for the POST request
        String ingredientJson = "{ \"name\": \"New Ingredient\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ingredientJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Ensure the ingredient was saved
        Ingredient savedIngredient = ingredientService.getAll().get(0);
        assertNotNull(savedIngredient.getId());
        assertEquals("New Ingredient", savedIngredient.getName());
    }

    @Test
    public void testGetIngredient() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Test Ingredient");
        ingredientService.save(ingredient);

        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/{id}", ingredient.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Test Ingredient")));
    }

    @Test
    public void testGetAllIngredients() throws Exception {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Ingredient 1");
        ingredientService.save(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Ingredient 2");
        ingredientService.save(ingredient2);

        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Ingredient 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("Ingredient 2")));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Ingredient to Delete");
        Ingredient savedIngredient = ingredientService.save(ingredient);

        mockMvc.perform(MockMvcRequestBuilders.delete("/ingredients/{id}", savedIngredient.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Ensure the ingredient was deleted
        Ingredient retrievedIngredient = ingredientService.get(savedIngredient.getId());
        assertNull(retrievedIngredient);
    }
}
