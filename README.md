# 🥘 Meals and Recipes Planner 📆

## Team Members:
- Ritika Deshpande (rgd2127)
- Vedangi Wagh (vw2287)
- Jyotsna Penumaka (jp4321)
- Surabhi Adhikari (sa4149)

**Development Platform**: MacOS

**Programming Language**: Java

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

## Files and Components

### Ingredient.java

`Ingredient.java` defines the `Ingredient` entity class that represents ingredients used in the application.

### IngredientController.java

The `IngredientController` manages API endpoints related to ingredients.

### Recipe.java

`Recipe.java` defines the `Recipe` entity class that represents recipes within the application.

### RecipeController.java

The `RecipeController` handles API endpoints for recipes, allowing users to perform operations like creating, updating, and deleting recipes.

### Client.java

`Client.java` defines the `Client` entity class that represents users or clients of the application.

### ClientController.java

The `ClientController` manages API endpoints for clients, enabling user-specific operations.

### ClientService.java

`ClientService.java` contains the business logic for handling client-related operations.

### ClientRepo.java

`ClientRepo.java` is the repository interface for the `Client` entity, allowing database interaction.

### IngredientService.java

`IngredientService.java` contains the business logic for handling ingredient-related operations.

### IngredientRepository.java

`IngredientRepository.java` is the repository interface for the `Ingredient` entity, allowing database interaction.

### RecipeService.java

`RecipeService.java` contains the business logic for handling recipe-related operations.

### RecipeRepository.java

`RecipeRepository.java` is the repository interface for the `Recipe` entity, allowing database interaction.

### RecipeControllerTest.java

`RecipeControllerTest.java` is the unit test class for testing the `RecipeController`.

### RecipeServiceTest.java

`RecipeServiceTest.java` is the unit test class for testing the `RecipeService`.

### IngredientServiceTest.java

`IngredientServiceTest.java` is the unit test class for testing the `IngredientService`.

### IngredientTests.java

`IngredientTests.java` is the unit test class for testing the `Ingredient` entity.

### RecipeTest.java

`RecipeTest.java` is the unit test class for testing the `Recipe` entity.



## How to Run

To run the application, follow these steps:

1. Make sure you have Java and Maven installed.
2. Clone the project repository.
3. Configure the database connection in `application.properties`.
4. Run the application using Maven: `mvn spring-boot:run`.

## API Endpoints

### Ingredient Endpoints

- `GET /api/v1/ingredients/{id}`: Get a specific ingredient by ID.
- `GET /api/v1/ingredients`: Get a list of all ingredients.
- `POST /api/v1/ingredients`: Create a new ingredient.
- `PUT /api/v1/ingredients/{id}`: Update an existing ingredient.
- `DELETE /api/v1/ingredients/{id}`: Delete an ingredient.

### Recipe Endpoints

- `GET /api/v1/recipes/{id}`: Get a specific recipe by ID.
- `GET /api/v1/recipes`: Get a list of all recipes.
- `POST /api/v1/recipes`: Create a new recipe.
- `PUT /api/v1/recipes/{id}`: Update an existing recipe.
- `DELETE /api/v1/recipes/{id}`: Delete a recipe.

### Client Endpoints

- `GET /api/v1/clients/{id}`: Get a specific client by ID.
- `GET /api/v1/clients`: Get a list of all clients.
- `POST /api/v1/clients`: Create a new client.
- `PUT /api/v1/clients/{id}`: Update an existing client.
- `DELETE /api/v1/clients/{id}`: Delete a client.



## Dependencies

List the main dependencies and technologies used in your project:

- Spring Boot: The framework for building Java applications.
- Spring Data JPA: For working with relational databases.
- PostgreSQL: As the relational database management system.
- Travis CI or GitHub Actions: For continuous integration and automated testing.
- JUnit: For unit testing.
- Postman: For API testing.

