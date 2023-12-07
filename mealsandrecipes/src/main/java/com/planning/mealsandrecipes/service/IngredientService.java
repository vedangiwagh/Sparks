package com.planning.mealsandrecipes.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.Json;
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
String prompt = "get nutritional model for ingredient "+ ingredientName+"  in the format below. {  name: Chicken Breast, calories: 165.0, fat: 3.6, carbohydrates: 0.0, fiber: 0.0, sugar: 0.0, protein: 31.0, sodium: 74.0";
String llmresponse = new String();
//        String openaiUrl = "https://api.openai.com/v1/engines/davinci/completions";
//
//        // Replace with your specific prompt format and API key handling
//        String requestBody = "{ \"prompt\": \"" + prompt + "\", \"max_tokens\": 20 }";
//
//        // Set the OpenAI API key in the headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + apiKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
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

        System.out.println(llmresponse);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(llmresponse);

            // Accessing values
            String name = jsonNode.get("choices").asText();
            String content = jsonNode.get("content").asText();


            // Printing values
            System.out.println("Name: " + name);
            System.out.println("Age: " + content);

        } catch (Exception e) {
            e.printStackTrace();
        }
//System.out.println(llmresponse);
return ingredient;
    }

//    public String getOpenAICompletion(String prompt) {
//        String openaiUrl = "https://api.openai.com/v1/engines/davinci/completions";
//
//        // Replace with your specific prompt format and API key handling
//        String requestBody = "{ \"prompt\": \"" + prompt + "\", \"max_tokens\": 150 }";
//
//        // Set the OpenAI API key in the headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + apiKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        // Make the HTTP request
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(openaiUrl, requestEntity, String.class);
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            return responseEntity.getBody();
//        } else {
//            // Handle error cases
//            return "Error: " + responseEntity.getStatusCode();
//        }
//    }


}
