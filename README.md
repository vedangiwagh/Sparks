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

Our project is a service that helps users plan their meals, manage recipes, and track nutritional values. Users can create, store, update, and retrieve recipes, plan their meals, and analyze the nutritional content of their diet. This project offers a comprehensive set of APIs that can be integrated into various applications for meal planning and nutrition tracking.

Key Features:
- Recipe Management: Create, store, update, and retrieve recipes in the database.
- Meal Planning: Plan daily, weekly, or monthly meals, and generate grocery lists based on the meal plan.
- Nutrition Analysis: Calculate the nutritional value of meals based on ingredients and quantities.

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

## Steps to run the Service 🎉 
#### Clone the project
```bash
git clone https://github.com/vedangiwagh/Sparks.git
```
#### Open the project
```bash
cd mealsandrecipes
```
#### Configure required variables located in src/main/resources/application-sql.properties
```bash
database=mysql
spring.cloud.gcp.sql.database-name=<DATABASE NAME>
spring.cloud.gcp.sql.instance-connection-name=<INSTANCE_CONNECTION_NAME>
spring.sql.init.mode=always
```
#### Configure required variables located in src/main/resources/application.properties
```bash
spring.profiles.active=mysql
```
##### We have used the Google Cloud PostGreSql in our project. To go forward with testing, you need to create databases in GCP and create necessary tables . Then add a credentials file to your project for accessing the Database instance from your project called credentials.json
```bash
export GOOGLE_APPLICATION_CREDENTIALS="/path/to/your/credentials.json"
```
#### Create a executable JAR file in terminal
```bash
mvnw clean install
```
#### Run the application in terminal
```bash
mvn spring-boot:run
```

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

## Error Handling

The API follows standard HTTP status codes for error responses. In case of an error, you will receive an appropriate status code and a JSON response with details.

## Ingredient Endpoints

### 1. Get a Specific Ingredient

- **Endpoint**: `GET /api/v1/ingredients/{id}`
- **Description**: Retrieve a specific ingredient by its unique identifier (ID).
- **Example Request**: `GET /api/v1/ingredients/1`
- **Response**: Returns the ingredient with the specified ID.

### 2. Get All Ingredients

- **Endpoint**: `GET /api/v1/ingredients`
- **Description**: Retrieve a list of all ingredients stored in the database.
- **Example Request**: `GET /api/v1/ingredients`
- **Response**: Returns a list of all ingredients.

### 3. Create a New Ingredient

- **Endpoint**: `POST /api/v1/ingredients`
- **Description**: Create a new ingredient by providing the necessary data in the request body.
- **Example Request**: `POST /api/v1/ingredients`
- **Request Body**: Contains ingredient details.
- **Response**: Returns the newly created ingredient.

### 4. Update an Existing Ingredient

- **Endpoint**: `PUT /api/v1/ingredients/{id}`
- **Description**: Update an existing ingredient by specifying its ID and providing updated data in the request body.
- **Example Request**: `PUT /api/v1/ingredients/1`
- **Request Body**: Contains updated ingredient details.
- **Response**: Returns the updated ingredient.

### 5. Delete an Ingredient

- **Endpoint**: `DELETE /api/v1/ingredients/{id}`
- **Description**: Delete an ingredient with the specified ID from the database.
- **Example Request**: `DELETE /api/v1/ingredients/1`
- **Response**: No content (204) if the ingredient is successfully deleted.

## Recipe Endpoints

### 1. Get a Specific Recipe

- **Endpoint**: `GET /api/v1/recipes/{id}`
- **Description**: Retrieve a specific recipe by its unique identifier (ID).
- **Example Request**: `GET /api/v1/recipes/1`
- **Response**: Returns the recipe with the specified ID.

### 2. Get All Recipes

- **Endpoint**: `GET /api/v1/recipes`
- **Description**: Retrieve a list of all recipes stored in the database.
- **Example Request**: `GET /api/v1/recipes`
- **Response**: Returns a list of all recipes.

### 3. Create a New Recipe

- **Endpoint**: `POST /api/v1/recipes`
- **Description**: Create a new recipe by providing the necessary data in the request body.
- **Example Request**: `POST /api/v1/recipes`
- **Request Body**: Contains recipe details.
- **Response**: Returns the newly created recipe.

### 4. Update an Existing Recipe

- **Endpoint**: `PUT /api/v1/recipes/{id}`
- **Description**: Update an existing recipe by specifying its ID and providing updated data in the request body.
- **Example Request**: `PUT /api/v1/recipes/1`
- **Request Body**: Contains updated recipe details.
- **Response**: Returns the updated recipe.

### 5. Delete a Recipe

- **Endpoint**: `DELETE /api/v1/recipes/{id}`
- **Description**: Delete a recipe with the specified ID from the database.
- **Example Request**: `DELETE /api/v1/recipes/1`
- **Response**: No content (204) if the recipe is successfully deleted.

## Client Endpoints

### 1. Get a Specific Client

- **Endpoint**: `GET /api/v1/clients/{id}`
- **Description**: Retrieve a specific client by their unique identifier (ID).
- **Example Request**: `GET /api/v1/clients/1`
- **Response**: Returns the client with the specified ID.

### 2. Get All Clients

- **Endpoint**: `GET /api/v1/clients`
- **Description**: Retrieve a list of all clients stored in the database.
- **Example Request**: `GET /api/v1/clients`
- **Response**: Returns a list of all clients.

### 3. Create a New Client

- **Endpoint**: `POST /api/v1/clients`
- **Description**: Create a new client by providing the necessary data in the request body.
- **Example Request**: `POST /api/v1/clients`
- **Request Body**: Contains client details.
- **Response**: Returns the newly created client.

### 4. Update an Existing Client

- **Endpoint**: `PUT /api/v1/clients/{id}`
- **Description**: Update an existing client by specifying their ID and providing updated data in the request body.
- **Example Request**: `PUT /api/v1/clients/1`
- **Request Body**: Contains updated client details.
- **Response**: Returns the updated client.

### 5. Delete a Client

- **Endpoint**: `DELETE /api/v1/clients/{id}`
- **Description**: Delete a client with the specified ID from the database.
- **Example Request**: `DELETE /api/v1/clients/1`
- **Response**: No content (204) if the client is successfully deleted.


## Dependencies

List the main dependencies and technologies used in your project:

- Spring Boot: The framework for building Java applications.
- Spring Data JPA: For working with relational databases.
- PostgreSQL: As the relational database management system.
- Travis CI or GitHub Actions: For continuous integration and automated testing.
- JUnit: For unit testing.
- Postman: For API testing.

