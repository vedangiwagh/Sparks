package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.model.MealModel;
import com.planning.mealsandrecipes.model.NutritionModel;
import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class MealPlanService {

    private final RecipeRepo recipeRepository;
    private final IngredientRepo ingredientRepo;

    private final RecipeIngredientRepo recipeIngredientRepo;


    public MealPlanService(RecipeRepo recipeRepository, IngredientRepo ingredientRepo, RecipeIngredientRepo recipeIngredientRepo) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepo = ingredientRepo;
        this.recipeIngredientRepo = recipeIngredientRepo;
    }


    public List<MealModel> getMeal(String mealType, String recipeType) {
        List<Recipe> recipes = recipeRepository.findByMealTypeAndRecipeType(mealType,recipeType);
        List<MealModel> mealModels = findIngredients(recipes);

        return mealModels;
    }

    public List<MealModel> getMealClientSpecific(String mealType, String recipeType, int clientId, String clientName, List<String> dietRestrictions, int calorieLimit) {

        Client client = new Client();
        client.setName(clientName);
        client.setClientID(clientId);

        List<Recipe> recipes = recipeRepository.findByMealTypeAndRecipeTypeAndClient(mealType,recipeType, clientId);
        List<MealModel> mealModels = findIngredients(recipes);
        //check if nutrition is null
        Iterator<MealModel> iterator1 = mealModels.iterator();
        while (iterator1.hasNext()) {
            MealModel mealModel = iterator1.next();

            // Check if it contains ingredients from diet restrictions
            if (mealModel.getNutritionModel().getCalories() == null || mealModel.getNutritionModel().getCalories() == 0.0) {
                iterator1.remove();
                continue; // Continue to the next iteration
            }
        }
        if(dietRestrictions != null || calorieLimit != 0) {
            Iterator<MealModel> iterator = mealModels.iterator();
            while (iterator.hasNext()) {
                MealModel mealModel = iterator.next();

                // Check if it contains ingredients from diet restrictions
                if (dietRestrictions != null && containsIngredient(mealModel.getIngredientList(), dietRestrictions)) {

                    iterator.remove();
                    continue; // Continue to the next iteration
                }

                // Check if calories exceed the limit
                if (mealModel.getNutritionModel().getCalories() != null && mealModel.getNutritionModel().getCalories() > calorieLimit) {
                    System.out.println("calories " + mealModel.getNutritionModel().getCalories());
                    iterator.remove();
                    continue;
                }
            }
        }


        return mealModels;
    }

    public List<MealModel> findIngredients(List<Recipe> recipes){
        List<MealModel> mealModels = new ArrayList<MealModel>();
        for(Recipe r : recipes){
            MealModel mealModel = new MealModel();
            NutritionModel nutritionModel = new NutritionModel();
            System.out.println(r.getRecipeName());
            mealModel.setRecipe(r);
            List<RecipeIngredient> recipeIngredients = recipeIngredientRepo.findByRecipeId(r.getRecipeId());
            List<String>  ingredientsList = new ArrayList<>();
            for (RecipeIngredient recipeIngredient : recipeIngredients)
            {
                String recipeIngredientQuantity = recipeIngredient.getQuantity(); //getting the recipe ingredients
                ingredientsList.add(recipeIngredientQuantity);
                Float quant = 0.0F;
                if(recipeIngredientQuantity != null) {
                    //regex pattern to detect number
                    String pattern = "^(\\d+)";
                    Pattern reg = Pattern.compile(pattern);
                    Matcher m = reg.matcher(recipeIngredientQuantity);//match the regex
                    if (m.find()) //if pattern found
                    {
                        String extractedText = m.group();
                        System.out.println(extractedText);
                        float num = Float.parseFloat(extractedText);
                        if (num > 10) {
                            quant = num / 100; // for quantity in gram calculation
                        } else {
                            quant = num; // for whole quantity calcuation
                        }
                    }
                }
                Ingredient ingredient = ingredientRepo.findById(recipeIngredient.getIngredient_id()).orElse(null);
                assert ingredient != null;
                if(nutritionModel.getCalories()!=null) {
                    //if the nutritiommodel object already exist
                    nutritionModel.setCalories(nutritionModel.getCalories() + quant * ingredient.getCalories());
                    nutritionModel.setFat(nutritionModel.getFat() + quant * ingredient.getFat());
                    nutritionModel.setCarbohydrates(nutritionModel.getCarbohydrates() + quant * ingredient.getCarbohydrates());
                    nutritionModel.setFiber(nutritionModel.getFiber() + quant * ingredient.getFiber());
                    nutritionModel.setSugar(nutritionModel.getSugar() + quant * ingredient.getSugar());
                    nutritionModel.setProtein(nutritionModel.getProtein() + quant * ingredient.getProtein());
                    nutritionModel.setSodium(nutritionModel.getSodium() + quant * ingredient.getSodium());
                }
                else{
                    nutritionModel.setCalories(quant * ingredient.getCalories());
                    nutritionModel.setFat(quant * ingredient.getFat());
                    nutritionModel.setCarbohydrates( quant * ingredient.getCarbohydrates());
                    nutritionModel.setFiber( quant * ingredient.getFiber());
                    nutritionModel.setSugar( quant * ingredient.getSugar());
                    nutritionModel.setProtein(quant * ingredient.getProtein());
                    nutritionModel.setSodium(quant * ingredient.getSodium());
                }

            }

            mealModel.setIngredientList(ingredientsList);
            mealModel.setNutritionModel(nutritionModel);
            mealModels.add(mealModel);
        }

        return mealModels;
    }

    public static boolean containsIngredient(List<String> ingredients, List<String> dietRestrictions) {

        for (String dietRestriction : dietRestrictions) {
            for (String ingredient : ingredients) {
                // Check if ingredient contains the diet restriction as a substring (case-insensitive)
                if (ingredient.toLowerCase().contains(dietRestriction.toLowerCase())) {
                    System.out.println("diet " + dietRestriction);
                    return true; // Return true if there is a match
                }
            }
        }

        return false;
    }

}
