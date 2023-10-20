# 🍽️ Meals and Recipes Planner 📆

## Team Members:
- Ritika Deshpande (rgd2127)
- Vedangi Wagh (vw2287)
- Jyotsna Penumaka (jp4321)
- Surabhi Adhikari (sa4149)

**Development Platform**: MacOS

**Programming Language**: Java

**GitHub Repository**: [Meals and Recipes Planner on GitHub](https://github.com/yourusername/meals-and-recipes-planner)

## Project Overview

### Project Description

Our project is a web application that helps users plan their meals, manage recipes, and track nutritional values. Users can create, store, update, and retrieve recipes, plan their meals, and analyze the nutritional content of their diet. This project offers a comprehensive set of APIs that can be integrated into various applications for meal planning and nutrition tracking.

Key Features:
- Recipe Management: Create, store, update, and retrieve recipes in the database.
- Meal Planning: Plan daily, weekly, or monthly meals, and generate grocery lists based on the meal plan.
- Nutrition Analysis: Calculate the nutritional value of meals based on ingredients and quantities.
- User Profiles: Users can create profiles with dietary preferences and restrictions.

### Client Types

Our project is designed to serve two main types of clients:

1. Senior Citizens Meal Planning App: Targeted at senior citizens with dietary restrictions, this app allows them to plan meals based on specific ingredients and nutritional values.

2. Fitness Meal Planner App: Fitness enthusiasts can use our service to customize and monitor their diet plans to meet their fitness goals.

### Testing

During the development of this project, we will perform various types of testing, including:

- Unit Testing: Testing individual components and subroutines to ensure they work correctly.
- API Testing/Integration Testing: Validating API functionality using tools like Postman to test load handling and concurrency.
- Persistent Data Testing: Ensuring data consistency and persistence, including simulating service restarts to monitor data integrity.

### Unit Testing Details

We use JUnit for unit testing in this project. Below are some key details:

- Test Runner: JUnit
- Test Cases: We have written unit tests for key components and functions to ensure they work correctly.
- Edge Cases: We've covered edge cases and error scenarios in our unit tests.
- Mocking: Mockito is used for mocking dependencies and simulating behavior.
- Test Reports: Test results and reports are generated and stored for analysis.

### Software Engineering Tools

We use the following tools and technologies for our project:

- Persistent Datastore: SQL Database
- Build or Package Manager: Maven
- Continuous Integration Tool: Travis CI, GitHub Actions
- Style Checker: GitHub Actions, IntelliJ style checker
- Test Runner for Unit Testing: JUnit
- Test Runner for API Testing: Postman
- Static Analysis Bug Finder: SonarQube

## Table of Contents

- [Project Structure](#project-structure)
- [Files and Components](#files-and-components)
- [How to Run](#how-to-run)
- [API Endpoints](#api-endpoints)
- [Dependencies](#dependencies)
- [Contributing](#contributing)

## Project Structure

Our project is structured as follows:

- Backend
  - Controllers
  - Services
  - Models (Entities)
- Frontend (if applicable)

## Files and Components

### Ingredient.java

`Ingredient.java` defines the `Ingredient` entity class that represents ingredients used in the application.

### IngredientController.java

The `IngredientController` manages API endpoints related to ingredients.

### Recipe.java

`Recipe.java` defines the `Recipe` entity class that represents recipes within the application.
