# 🥘 SPARKS plans your meals 🍽️ 

## Team Members:
- Ritika Deshpande (rgd2127)
- Vedangi Wagh (vw2287)
- Jyotsna Penumaka (jp4321)
- Surabhi Adhikari (sa4149)

**Development Platform**: MacOS

**Programming Language**: Java

**GitHub Repository**: [Sparks on GitHub](https://github.com/vedangiwagh/Sparks)

## Project Overview

### Part 2: Project Description

Our project aims to create a set of APIs that assist users in meal planning, online grocery ordering, and nutritional value analysis. These APIs can be integrated into various applications, allowing users to plan their meals, order groceries, and obtain nutritional information.

Key Features:
- Recipe Management APIs: Create, store, and update recipes in the database. Generate grocery lists based on recipe requirements and ingredients.
- Meal Planning API: Create and manage meal plans. Generate grocery lists based on meal plans.
- Nutrition Value API: Calculate the nutritional value of meal plans based on ingredients and quantities.

We will store persistent data, including selected recipes, meal schedules, dietary preferences, and user-specific dietary restrictions. This data can be accessed, modified, and retrieved by users at any time.

### Part 3: Client Types

We envision two distinct kinds of clients that may use our service:

1. Senior Citizen Meal Planning App: Targeted towards senior citizens with dietary restrictions, this app can use our service to plan meals based on ingredients and nutritional values.
2. Fitness Meal Planner App: Fitness enthusiasts who require strict dietary monitoring can use our service to tailor meals to their specific requirements.

### Part 4: Testing

During the first iteration, we will perform the following types of testing:

- Unit Testing: Testing individual subroutines and components in isolation, including edge cases and error scenarios.
- API Testing/Integration Testing: Using tools like Postman, we will test the APIs for load handling and concurrency, ensuring they can handle multiple client instances.
- Persistent Data Testing: To ensure data consistency, we will simulate service restarts and monitor data persistence using JUnit.

### Part 5: Software Engineering Tools

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

Explain the overall structure of your project. For example:

- Backend
  - Controllers
  - Services
  - Models (Entities)
- Frontend
  - ...
- Configuration
- ...

## Files and Components

### Ingredient.java

This file defines the `Ingredient` entity class, which represents ingredients used in the application.

### IngredientController.java

The `IngredientController` handles API endpoints related to ingredients.

### Recipe.java

This file defines the `Recipe` entity class, representing recipes in the application.

### RecipeController.java

The `RecipeController` manages API endpoints for recipes.

## How to Run

Explain how to run the project, including any prerequisites or setup. For example:

1. Make sure you have Java and Maven installed.
2. Clone the project repository.
3. Configure the database connection in `application.properties`.
4. Run the application using Maven: `mvn spring-boot:run`.

## API Endpoints

List and describe the available API endpoints, including input parameters and expected responses.

### Ingredient Endpoints

- `GET /api/v1/ingredients/{id}` - Get a specific ingredient by ID.
- `GET /api/v1/ingredients` - Get a list of all ingredients.
- ...

### Recipe Endpoints

- `GET /api/v1/recipes/{id}` - Get a specific recipe by ID.
- `GET /api/v1/recipes` - Get a list of all recipes.
- ...

## Dependencies

List the main dependencies and technologies used in your project. For example:

- Spring Boot
- Spring Data JPA
- Swagger
- ...
