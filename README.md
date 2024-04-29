# Training Center Registry

## Overview
The Training Center Registry project is aimed at providing a centralized system for managing government-funded training centers. It allows users to create, update, delete, and retrieve information about training centers. The project is built using Java Spring Boot and Hibernate, with MySQL as the database.

## Features
- **Create Training Center:** Users can create a new training center by providing details such as name, code, address, capacity, courses offered, contact email, and phone number.
- **Update Training Center:** Users can update various attributes of a training center, including courses offered, contact email, phone number, and address.
- **Retrieve Training Center:** Users can retrieve information about training centers based on various criteria such as center code, center name, city, state, student capacity, and creation date.
- **Delete Training Center:** Users can delete a training center by providing its center code.

## Tech Stack
- **Java:** The core programming language used for developing the backend logic.
- **Spring Boot:** The framework used for building the RESTful APIs and handling dependency injection.
- **Hibernate:** The ORM (Object-Relational Mapping) framework used for mapping Java objects to database tables and vice versa.
- **MySQL:** The relational database management system used for storing training center data.

## Project Structure
- **Controller:** Contains the RESTful API endpoints for handling HTTP requests and responses.
- **Service:** Implements the business logic for performing CRUD operations on training centers.
- **Model:** Defines the data models, including the TrainingCenter and Address entities.
- **Repository:** Provides an interface for performing CRUD operations on the database.

## Setup Instructions
1. Clone the repository to your local machine.
2. Set up a MySQL database and configure the database connection properties in the `application.properties` file.
3. Build and run the project using Maven or your preferred IDE.
4. Access the RESTful APIs using tools like Postman or integrate them into your frontend application.

## API Documentation
- **POST /trainingCenter:** Create a new training center.
- **GET /centers:** Get a list of all training centers.
- **PUT /newCourses:** Update the courses offered by a training center.
- **PUT /contactEmail:** Update the contact email of a training center.
- **PUT /contactPhone:** Update the contact phone number of a training center.
- **PUT /address:** Update the address of a training center.
- **GET /centerByCenterCode:** Get a training center by its center code.
- **GET /centerByCenterName:** Get a training center by its name.
- **GET /centersInAState:** Get a list of training centers in a specific state.
- **GET /centersInACity:** Get a list of training centers in a specific city.
- **GET /centersByCapacity:** Get a list of training centers based on student capacity.
- **GET /centersWithCapacityGreaterThan:** Get a list of training centers with capacity greater than a specified value.
- **GET /centersWithCapacityLessThan:** Get a list of training centers with capacity less than a specified value.
- **GET /centersFoundedBetween:** Get a list of training centers founded between two specified dates.
- **DELETE /center:** Delete a training center by its center code.
- **GET /centersWithMultipleCriteria:** Get a list of training centers based on multiple criteria.

## Contributors
- Manikanta Kotekal Methukula

