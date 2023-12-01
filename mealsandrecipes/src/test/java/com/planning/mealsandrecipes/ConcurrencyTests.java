package com.planning.mealsandrecipes;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.model.MealModel;
import com.planning.mealsandrecipes.model.MealRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConcurrencyTests {
    public ConcurrencyTests() {
        // Constructor logic, if needed
    }
    @LocalServerPort
    private int port;

    RestTemplate restTemplate = new RestTemplate();


    @Test
    public void testConcurrentGetMealPlanRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<String>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> restTemplate.getForObject("http://localhost:" + port + "/meals/Main Course/Vegetarian", String.class));
        }

        List<Future<String>> futures = executorService.invokeAll(tasks);

        for (Future<String> future : futures) {
            // Add assertions or validations on the response
//            System.out.println("tests");
            String response = future.get();
            assertNotNull(response);
        }

        executorService.shutdown();
    }

    @Test
    public void testConcurrentGetIngredientsRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<String>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> restTemplate.getForObject("http://localhost:" + port + "/ingredients", String.class));
        }

        List<Future<String>> futures = executorService.invokeAll(tasks);

        for (Future<String> future : futures) {
            // Add assertions or validations on the response
//            System.out.println("tests");
            String response = future.get();
            assertNotNull(response);
        }

        executorService.shutdown();
    }

    @Test
    public void testConcurrentGetRecipesRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<String>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> restTemplate.getForObject("http://localhost:" + port + "/recipes", String.class));
        }

        List<Future<String>> futures = executorService.invokeAll(tasks);

        for (Future<String> future : futures) {
            // Add assertions or validations on the response
//            System.out.println("tests");
            String response = future.get();
            assertNotNull(response);
        }

        executorService.shutdown();
    }

    @Test
    public void testConcurrentPostIngredientsRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<ResponseEntity<String>>> tasks = new ArrayList<>();

        // Replace String.class with the expected response entity type for your POST endpoint
        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> restTemplate.postForEntity("http://localhost:" + port + "/recipe-ingredients/bulk", createTestRecipeIngredients(), String.class));
        }

        List<Future<ResponseEntity<String>>> futures = executorService.invokeAll(tasks);

        for (Future<ResponseEntity<String>> future : futures) {
            // Add assertions or validations on the response
            ResponseEntity<String> responseEntity = future.get();
            assertNotNull(responseEntity);
            System.out.println("tests");
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        }

        executorService.shutdown();
    }

    @Test
    public void testConcurrentCreateRecipeIngredientRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<ResponseEntity<RecipeIngredient>>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            // Adjust the test data creation based on your requirements
            RecipeIngredient testRecipeIngredient = createTestRecipeIngredient();

            tasks.add(() -> restTemplate.postForEntity("http://localhost:" + port + "/recipe-ingredients", testRecipeIngredient, RecipeIngredient.class));
        }

        List<Future<ResponseEntity<RecipeIngredient>>> futures = executorService.invokeAll(tasks);

        for (Future<ResponseEntity<RecipeIngredient>> future : futures) {
            ResponseEntity<RecipeIngredient> responseEntity = future.get();
            assertNotNull(responseEntity);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

            // Add further assertions based on your application's behavior
            RecipeIngredient createdIngredient = responseEntity.getBody();
            assertNotNull(createdIngredient);
            // Add more assertions as needed
        }

        executorService.shutdown();
    }

    @Test
    public void testConcurrentCreateRecipeRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<ResponseEntity<Recipe>>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            // Adjust the test data creation based on your requirements
            Recipe testRecipe = createTestRecipe();

            tasks.add(() -> restTemplate.postForEntity("http://localhost:" + port + "/recipes", testRecipe, Recipe.class));
        }

        List<Future<ResponseEntity<Recipe>>> futures = executorService.invokeAll(tasks);

        for (Future<ResponseEntity<Recipe>> future : futures) {
            ResponseEntity<Recipe> responseEntity = future.get();
            assertNotNull(responseEntity);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

            // Add further assertions based on your application's behavior
            Recipe createdRecipe = responseEntity.getBody();
            assertNotNull(createdRecipe);
            // Add more assertions as needed
        }

        executorService.shutdown();
    }

    private Recipe createTestRecipe() {
        // Create and return a test Recipe
        Recipe recipe = new Recipe();
        recipe.setRecipeName("test");
        recipe.setRecipeType("test");
        recipe.setMealType("test");
        recipe.setDescription("test");
        recipe.setInstructions("test");
        recipe.setCookingTime(0);
        recipe.setPreparationTime(0);
        recipe.setClient(4);
        recipe.setRecipeId(1);

        return recipe;
    }

    private List<RecipeIngredient> createTestRecipeIngredients() {
        // Create and return a list of test RecipeIngredients
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient_id(Long.valueOf(0));
        recipeIngredient.setQuantity("0");
        recipeIngredient.setRecipeId(0);
        recipeIngredients.add(recipeIngredient);
        return recipeIngredients;
    }

    private RecipeIngredient createTestRecipeIngredient() {
        // Create and return a test RecipeIngredient
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient_id(Long.valueOf(0));
        recipeIngredient.setQuantity("0");
        recipeIngredient.setRecipeId(0);
        return recipeIngredient;
    }

    @Test
    public void testConcurrentGetMealClientSpecificRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 7;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<Map.Entry<MealRequest, List<MealModel>>>> tasks = new ArrayList<>();

        for (int i = 2; i < numberOfThreads; i++) {
            MealRequest mealRequest = new MealRequest();
            mealRequest.setMealType("Main Course");
            mealRequest.setRecipeType("Vegetarian");
            mealRequest.setClientId(i); // Assuming clientId can be different for each thread
            mealRequest.setClientName("test");

            tasks.add(() -> {
                // Use exchange method to handle generic types
                ResponseEntity<List<MealModel>> responseEntity = restTemplate.exchange(
                        "http://localhost:" + port + "/meals/getMealClientSpecific",
                        HttpMethod.POST,
                        new HttpEntity<>(mealRequest),
                        new ParameterizedTypeReference<List<MealModel>>() {}
                );

//                return responseEntity.getBody();
                return Map.entry(mealRequest, responseEntity.getBody());
            });
        }

        List<Future<Map.Entry<MealRequest, List<MealModel>>>> futures = executorService.invokeAll(tasks);

        for (Future<Map.Entry<MealRequest, List<MealModel>>> future : futures) {
            Map.Entry<MealRequest, List<MealModel>> entry = future.get();
            assertNotNull(entry.getValue());
            for (MealModel mealModel : entry.getValue()) {
                System.out.println(mealModel.getRecipe().getClient());
                //check request response client
                assertEquals(mealModel.getRecipe().getClient(), entry.getKey().getClientId());
            }
        }

        executorService.shutdown();
    }

}
