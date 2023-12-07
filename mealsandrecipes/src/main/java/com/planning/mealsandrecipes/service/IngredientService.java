package com.planning.mealsandrecipes.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.Json;
import com.planning.mealsandrecipes.model.NutritionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class IngredientService {
   // Add your API key in application.properties or application.yml
    private String apiKey = "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";
    @Autowired
    private IngredientRepo ingredientRepository;

    // Save an ingredient to the repository.
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // Retrieve an ingredient by its ID.
    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public List<Ingredient> getByName(String name) {
        return ingredientRepository.findByNameContainingIgnoreCase(name);
    }

    // Retrieve a list of all ingredients.
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    // Delete an ingredient by its ID.
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> saveAll(List<Ingredient> ingredientsList) {
        return ingredientRepository.saveAll(ingredientsList);
    }

    public Ingredient createIngredientUsingLLM(String ingredientName)
    {
        Ingredient ingredient = new Ingredient() ;
        System.out.println("In llm part now");
        System.out.println(ingredientName);
String prompt = "in json format get nutritional model for ingredient "+ ingredientName+"  in the format below. {  name: Chicken Breast, calories: 165.0, fat: 3.6, carbohydrates: 0.0, fiber: 0.0, sugar: 0.0, protein: 31.0, sodium: 74.0";
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
        ingredient = convertToJson(llmresponse);
        int size = ingredientRepository.findAll().size();
        ingredient.setId(Long.valueOf(size + 1));
//System.out.println(llmresponse);
        ingredientRepository.save(ingredient);
    return ingredient;
    }


    public Ingredient convertToJson(String s){
//        String jsonString = "{\"id\":\"chatcmpl-8TFb3JcYsmn2Bu5O4rjmy1E6abKKB\",\"object\":\"chat.completion\",\"created\":1701982157,\"model\":\"gpt-3.5-turbo-0613\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"The nutritional model for spinach is as follows:\\n{\\n  name: \\\"Spinach\\\",\\n  calories: 23.0,\\n  fat: 0.4,\\n  carbohydrates: 3.6,\\n  fiber: 2.2,\\n  sugar: 0.4,\\n  protein: 2.9,\\n  sodium: 79.0\\n}\"},\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":74,\"completion_tokens\":74,\"total_tokens\":148}}";
        String jsonString = s;
        Ingredient ingredient = null;
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert string to JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Extract content from JsonNode
            String content = jsonNode
                    .path("choices")
                    .path(0)
                    .path("message")
                    .path("content")
                    .asText();

            // Print the content
            System.out.println("Content: " + content);

            try {
                // Create ObjectMapper
                ObjectMapper objectMapper1 = new ObjectMapper();

                // Remove newlines and extra spaces to make the content a valid JSON
                String cleanedJsonString = content.replaceAll("\n", "").replaceAll(" +", " ");

                // Extract the JSON substring
                int startIndex = cleanedJsonString.indexOf('{');
                int endIndex = cleanedJsonString.lastIndexOf('}');
                String jsonSubstring = cleanedJsonString.substring(startIndex, endIndex + 1);
                jsonSubstring = jsonSubstring.replaceAll("(\\w+):", "\"$1\":");
                System.out.println("JSON AFTER :::"+ jsonSubstring);
                // Map string to Ingredient
                ingredient = objectMapper1.readValue(jsonSubstring, Ingredient.class);

                // Print the mapped Ingredient
                System.out.println("Ingredient: " + ingredient);

            } catch (Exception e) {
                e.printStackTrace();
            }
//            ingredient = objectMapper.readValue(substringAfterColon, Ingredient.class);
            return ingredient;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredient;
    }

}
