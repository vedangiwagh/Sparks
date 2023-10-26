package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.MealModel;
import com.planning.mealsandrecipes.NutritionModel;
import com.planning.mealsandrecipes.entity.Ingredient;
import com.planning.mealsandrecipes.entity.Recipe;
import com.planning.mealsandrecipes.entity.RecipeIngredient;
import com.planning.mealsandrecipes.repository.IngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import com.planning.mealsandrecipes.repository.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        List<MealModel> mealModels = new ArrayList<MealModel>();

        List<Recipe> recipes = recipeRepository.findByMealTypeAndRecipeType(mealType,recipeType);
        for(Recipe r : recipes){
            MealModel mealModel = new MealModel();
            NutritionModel nutritionModel = new NutritionModel();
            mealModel.setRecipe(r);
            List<RecipeIngredient> recipeIngredients = recipeIngredientRepo.findByRecipeId(r.getRecipeId());
            List<String>  ingredientsList = new ArrayList<>();
            for (RecipeIngredient ri : recipeIngredients)
            {
                String s = ri.getQuantity();
                ingredientsList.add(s);

                String pattern2 = "^(\\d+)";

                Pattern reg2 = Pattern.compile(pattern2);

                Matcher m2 = reg2.matcher(s);
                Float quant = 0.0F;
                    if(m2.find())
                    {
                        String extractedText = m2.group();

                        System.out.println(extractedText);
                        float num = Float.parseFloat(extractedText);
                        if(num>10){
                            quant=num/100;
                        }
                        else{
                            quant=num;
                        }
                    }

                Ingredient ingredient = ingredientRepo.findById(ri.getIngredient_id()).orElse(null);
                assert ingredient != null;
                if(nutritionModel.getCalories()!=null) {
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
//        System.out.println("MEALPLAN---" + mealModel.getRecipe().getRecipeName() );

        return mealModels;
    }
}
