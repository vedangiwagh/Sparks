package com.planning.mealsandrecipes.controller;
import com.planning.mealsandrecipes.model.MealModel;
import com.planning.mealsandrecipes.model.MealRequest;
import com.planning.mealsandrecipes.service.MealPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealPlanController {
    @Autowired
    private MealPlanService mealPlanService;


    @Operation(summary = "Returns a specific mealPlan based on given parameters.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of mealPlans")
    @ApiResponse(responseCode = "404", description = "no mealPlan")
    @GetMapping("/{mealType}/{recipeType}")
    public List<MealModel> getMeal(@PathVariable String mealType, @PathVariable String recipeType) {
        return mealPlanService.getMeal(mealType, recipeType);
    }


    @PostMapping("/getMealClientSpecific")
    public List<MealModel> getMealClientSpecific(@RequestBody MealRequest mealRequest) {
        String mealType = mealRequest.getMealType();
        String recipeType = mealRequest.getRecipeType();
        int clientId = mealRequest.getClientId();
        String clientName = mealRequest.getClientName();
        List<String> dietRestrictions = mealRequest.getDietRestriction();
        int calorieLimit = mealRequest.getCalorieLimit();
        return mealPlanService.getMealClientSpecific(mealType, recipeType, clientId, clientName, dietRestrictions, calorieLimit);
    }


}
