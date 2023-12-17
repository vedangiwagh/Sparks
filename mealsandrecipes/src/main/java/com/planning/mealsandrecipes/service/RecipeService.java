package com.planning.mealsandrecipes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    public  RecipeRepo recipeRepository;

    private final IngredientRepo ingredientRepo;

    private final RecipeIngredientRepo recipeIngredientRepo;


    public RecipeService(RecipeRepo recipeRepository, IngredientRepo ingredientRepo, RecipeIngredientRepo recipeIngredientRepo) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepo = ingredientRepo;
        this.recipeIngredientRepo = recipeIngredientRepo;
    }
    private String apiKey = "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";

    // Create a new recipe and save it to the repository.
    public Integer createRecipe(Recipe recipe) {
        Recipe createdRecipe = recipeRepository.save(recipe);
        // Return the auto-generated ID
        return createdRecipe.getRecipeId();
    }

    // Retrieve a recipe by its ID.
    public Recipe getRecipeById(Integer recipeId) {
        if (isDatabaseEmpty()) {
            throw new ResourceNotFoundException("No recipes found");
        }
        Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId))
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Recipe does not exist with id :" + recipeId));
        return recipe;
    }

    // Retrieve a list of all recipes.
    public List<Recipe> getAllRecipes() {
        if (isDatabaseEmpty()) {
            throw new ResourceNotFoundException("No recipes found");
        }
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    public List<Recipe> getAllRecipesByClient(int client) {
        if (isDatabaseEmpty()) {
            throw new ResourceNotFoundException("No recipes found");
        }
        List<Recipe> recipes = recipeRepository.findAllRecipesByClient(client);
        return recipes;
    }

    public List<Recipe> findRecipesByNameAndClient(String recipeName, int client) {
        System.out.println("RECIPE NAME" + recipeName);
        return recipeRepository.findByRecipeNameContainingIgnoreCaseAndClient(recipeName,client);
    }

    // Update an existing recipe with the provided data.
    public Recipe updateRecipe(Integer recipeId, Recipe recipeDetails) {
        Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId))
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Recipe not exist with id :" + recipeId));
        recipe.setRecipe(recipeDetails);
        Recipe updatedRecipe = recipeRepository.save(recipe);
//        System.out.println("CLIENT NOT FOUND");
        return updatedRecipe;
    }

    public List<Recipe> findRecipesByName(String recipeName) {
        System.out.println("RECIPE NAME" + recipeName);
        return recipeRepository.findByRecipeNameContainingIgnoreCase(recipeName);
    }

    public List<Recipe> findRecipesforClient(String mealType, String recipeType, int client) {
        return recipeRepository.findByMealTypeAndRecipeTypeAndClient(mealType, recipeType, client);
    }
    // Delete a recipe by its ID.
    public void deleteRecipe(Integer recipeId) {
        recipeRepository.deleteById(Long.valueOf(recipeId));
    }

    public boolean isDatabaseEmpty() {
        return recipeRepository.count() == 0L;
    }


    //method create recipe using llm
    //{
    // add new recipe - use existing method
    // check if ingredient present using name
    //      if present get id and save to array
    //      else add new ingredient and get new id and save in array
    // add recipe-ingredient mapping for particular recipe
    // }
    public void insertIntoDB(Recipe recipe, ArrayList<String> ingredients){
//        int recipeId = recipeRepository.findAll().size() + 1;
        recipe.setClient(2);
        createRecipe(recipe);
        System.out.println("Added recipe with id : " + recipe.getRecipeId());
        for (String ingredient : ingredients) {
            List<Ingredient> ingredientList = ingredientRepo.findByNameContainingIgnoreCase(ingredient);
            if(!ingredientList.isEmpty()){
                Ingredient i = ingredientList.get(0);
                RecipeIngredient recipeIngredient = new RecipeIngredient();
                recipeIngredient.setIngredient_id(i.getId());
                recipeIngredient.setRecipeId(recipe.getRecipeId());
                recipeIngredientRepo.save(recipeIngredient);
                System.out.println("Added recipe Ingredient mapping for " + i.getName());
            }

        }
    }

    public Recipe generateRecipeUsingLLM(String recipeName){
        Recipe recipe = null;
        System.out.println("In llm part now");
        System.out.println(recipeName);
        String prompt = "given recipe name generate sample recipe in json format like below - recipe: recipe: { recipeName: bean and rice bowl, description: A protein-packed bowl with black beans and rice., instructions: 1. Cook beans and rice. 2. Combine in a bowl., preparationTime: 20, cookingTime: 25,mealType: Main Course, recipeType: Vegetarian, client: 2},and ingredients list like below - ingredientList: [ Black Beans,Rice ] for recipename = " + recipeName;
        String llmresponse = new String();
        String openaiUrl = "https://api.openai.com/v1/chat/completions";

        // Construct request body
        String requestBody = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}],"
                + "\"temperature\": 0.9"
                + "}";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the HTTP request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(openaiUrl, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            llmresponse= responseEntity.getBody();
        } else {
            // Handle error cases
            System.err.println( "Error: " + responseEntity.getStatusCode());
        }
        System.out.println("LLM generated :" + llmresponse);
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert string to JsonNode
            JsonNode jsonNode = objectMapper.readTree(llmresponse);

            // Extract content from JsonNode
            String content = jsonNode
                    .path("choices")
                    .path(0)
                    .path("message")
                    .path("content")
                    .asText();

            // Print the content
            System.out.println("Content: " + content);
            recipe = extractJson(content, recipe );

        } catch (Exception e) {

//            e.printStackTrace();

            throw new ResourceNotFoundException("no content found " + e);
//            e.printStackTrace();
        }
        recipe.setClient(2);
        return recipe;
    }

    public Recipe extractJson(String content, Recipe recipe) {
        ArrayList<String> ingredientList = null;

//        String jsonString = "{\n" +
//                "  \"recipe\": {\n" +
//                "    \"recipeName\": \"butter chicken\",\n" +
//                "    \"description\": \"A flavorful Indian dish made with tender chicken in a rich and creamy tomato-based sauce.\",\n" +
//                "    \"instructions\": \"1. Marinate the chicken in yogurt and spices. 2. Sear the chicken pieces. 3. Prepare the sauce by cooking onions, garlic, ginger, and spices. 4. Add tomatoes and simmer. 5. Blend the sauce until smooth. 6. Cook the chicken in the sauce until tender. 7. Finish with heavy cream and butter. 8. Serve hot with naan or rice.\",\n" +
//                "    \"preparationTime\": 30,\n" +
//                "    \"cookingTime\": 40,\n" +
//                "    \"mealType\": \"Main Course\",\n" +
//                "    \"recipeType\": \"Non-vegetarian\",\n" +
//                "    \"client\": 4\n" +
//                "  },\n" +
//                "  \"ingredientList\": [\n" +
//                "    \"Chicken\",\n" +
//                "    \"Yogurt\",\n" +
//                "    \"Onions\",\n" +
//                "    \"Garlic\",\n" +
//                "    \"Ginger\",\n" +
//                "    \"Tomatoes\",\n" +
//                "    \"Spices\",\n" +
//                "    \"Heavy cream\",\n" +
//                "    \"Butter\",\n" +
//                "    \"Naan\",\n" +
//                "    \"Rice\"\n" +
//                "  ]\n" +
//                "}";
        String jsonString = content;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Extract recipe
            JsonNode recipeNode = jsonNode.get("recipe");
            recipe = objectMapper.treeToValue(recipeNode, Recipe.class);

            // Extract ingredient list
            JsonNode ingredientListNode = jsonNode.get("ingredientList");
            ingredientList = objectMapper.convertValue(ingredientListNode, ArrayList.class);

            // Print the extracted data
            System.out.println("Recipe: " + recipe);
            System.out.println("Ingredient List: " + ingredientList);

        } catch (IOException e) {

            throw new ResourceNotFoundException("no json found recipe not generated " + e);

//            e.printStackTrace();
        }

        insertIntoDB(recipe, ingredientList);

        return recipe;
    }
}
