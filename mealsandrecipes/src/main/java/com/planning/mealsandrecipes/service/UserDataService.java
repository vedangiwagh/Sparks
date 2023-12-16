package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.UserData;
import com.planning.mealsandrecipes.exception.ResourceNotFoundException;
import com.planning.mealsandrecipes.repository.RecipeIngredientRepo;
import com.planning.mealsandrecipes.repository.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataService {
//    @Autowired
//    private UserDataRepo userRepository;

    private final UserDataRepo userRepository;
    @Autowired
    public UserDataService(UserDataRepo userRepository) {
        this.userRepository = userRepository;
    }
    public UserData createUser(UserData userData) {
        // Implement validation logic if needed
        return userRepository.save(userData);
    }

    public UserData updatePassword(String userEmail, String newPassword) {
        UserData user = userRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("User Not found - "+ userEmail));
        if (user != null) {
            user.setPassword(newPassword);
            return userRepository.save(user);
        }
        return null; // User not found
    }

    public UserData getUserByEmail(String userEmail) {
        return userRepository.findById(userEmail).orElse(null);
    }

//    public List<UserData> getUsersByClientId(Long clientId) {
//        return userRepository.findByClientId(clientId);
//    }
}

