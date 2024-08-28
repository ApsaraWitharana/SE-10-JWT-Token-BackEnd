# JWT Token Authentication using Spring and MySQL

A Spring-based project for generating and validating JSON Web Tokens (JWTs) using MySQL as the database.

## Overview

This project provides a secure authentication mechanism using JWT tokens, which can be used to authenticate and authorize users in a web application. The project uses Spring Boot as the framework, MySQL as the database, and JWT (JSON Web Tokens) for token generation and validation.

## Features

* User registration and login functionality
* JWT token generation and validation using HS256 algorithm
* MySQL database for storing user credentials and token information
* Spring Security for authentication and authorization
* RESTful API for token generation and validation

## Technologies Used

* Spring Boot 3.3.2
* MySQL 8.0.33
* JWT 0.9.1
* jaxb-api 2.3.1
* modelmapper 3.1.1
* Spring Security 5.3.2

## Installation

### Prerequisites

* Java 17 or higher
* MySQL 8.0.33 or higher
* Maven 4.0.0 or higher

### Steps to Install

1. Clone the repository using `git clone https://github.com/ApsaraWitharana/SE-10-JWT-Token.git`
2. Create a MySQL database and update the `application.properties` file with the database credentials
3. Run the project using `mvn spring-boot:run`
4. Use a tool like Postman or cURL to test the API endpoints

## API Endpoints

### User Registration

* `POST /register`: Register a new user
	+ Request Body: `name`, `password`, `email`
	+ Response: `201 Created` with a JSON response containing the user ID

### User Login

* `POST /login`: Login an existing user
	+ Request Body: `email`, `password`
	+ Response: `200 OK` with a JSON response containing the JWT token

### Token Validation

* `GET /validate`: Validate a JWT token
	+ Request Header: `Authorization` with the JWT token
	+ Response: `200 OK` if the token is valid, `401 Unauthorized` otherwise

### Token Refresh

* `POST /refresh`: Refresh an existing JWT token
	+ Request Header: `Authorization` with the JWT token
	+ Response: `200 OK` with a new JWT token


### JWT-Token - Postman Document 

- https://documenter.getpostman.com/view/35385905/2sAXjDcuVP


## Database Schema

The project uses the following database schema:

```sql
CREATE TABLE users (
  uid UUID PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  CompanyName VARCHAR(100) NOT NULL,
  role VARCHAR(100) NOT NULL

);

