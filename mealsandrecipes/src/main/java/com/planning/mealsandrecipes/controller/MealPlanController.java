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

//    @Operation(summary = "Returns a specific mealPlan based on given parameters specific to client.")
//    @ApiResponse(responseCode = "200", description = "Successful retrieval of mealPlan")
//    @ApiResponse(responseCode = "404", description = "no mealPlan for client")
//    @GetMapping("/{mealType}/{recipeType}/{clientId}/{clientName}")
//    public List<MealModel> getMealClientSpecific(@PathVariable String mealType, @PathVariable String recipeType,
//                                   @PathVariable int clientId, @PathVariable String clientName) {
//        return mealPlanService.getMealClientSpecific(mealType, recipeType, clientId, clientName);
//    }

    @PostMapping("/getMealClientSpecific")
    public List<MealModel> getMealClientSpecific(@RequestBody MealRequest mealRequest) {
        String mealType = mealRequest.getMealType();
        String recipeType = mealRequest.getRecipeType();
        int clientId = mealRequest.getClientId();
        String clientName = mealRequest.getClientName();
        return mealPlanService.getMealClientSpecific(mealType, recipeType, clientId, clientName);
    }


}
