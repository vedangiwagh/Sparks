package com.planning.mealsandrecipes.controller;

import com.planning.mealsandrecipes.entity.UserData;
import com.planning.mealsandrecipes.service.RecipeIngredientService;
import com.planning.mealsandrecipes.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserDataController {
//    @Autowired
//    private UserDataService userService;

    private final UserDataService userService;
    @Autowired
    public UserDataController(UserDataService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserData createUser(@RequestBody UserData userData) {
        return userService.createUser(userData);
    }

    @PutMapping("/updatePassword/{userEmail}/{newPassword}")
    public UserData updatePassword(@PathVariable String userEmail, @PathVariable String newPassword) {
        return userService.updatePassword(userEmail, newPassword);
    }

    @GetMapping("/getUserByEmail/{userEmail}")
    public UserData getUserByEmail(@PathVariable String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    @GetMapping("/login/{userEmail}/{password}")
    public String login(@PathVariable String userEmail, @PathVariable String password) {
        return userService.login(userEmail,password);
    }

//    @GetMapping("/getUsersByClientId/{clientId}")
//    public List<UserData> getUsersByClientId(@RequestParam Long clientId) {
//        return userService.getUsersByClientId(clientId);
//    }
}
