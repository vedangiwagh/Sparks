package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.MealModel;
import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        List<MealModel> mealModels = new ArrayList<MealModel>();

        List<Recipe> recipes = recipeRepository.findByMealTypeAndRecipeType(mealType,recipeType);
        for(Recipe r : recipes){
            MealModel mealModel = new MealModel();
            mealModel.setRecipe(r);
            mealModels.add(mealModel);
        }
//        System.out.println("MEALPLAN---" + mealModel.getRecipe().getRecipeName() );

        return mealModels;
    }

    public List<MealModel> getMealClientSpecific(String mealType, String recipeType, int clientId, String clientName) {
        List<MealModel> mealModels = new ArrayList<MealModel>();
        Client client = new Client();
        client.setName(clientName);
        client.setClientID(clientId);

        List<Recipe> recipes = recipeRepository.findByMealTypeAndRecipeTypeAndClient(mealType,recipeType, client);
        for(Recipe r : recipes){
            MealModel mealModel = new MealModel();
            mealModel.setRecipe(r);
            mealModels.add(mealModel);
        }
//        System.out.println("MEALPLAN---" + mealModel.getRecipe().getRecipeName() );

        return mealModels;
    }
}
