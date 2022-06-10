# REST API for an E-Commerce Application

* This project is developed by a team of 5 members during our project week in Masai School, Bengaluru. 
* We have developed a REST API for an e-commerce platform. This API performs the fundamental operations of any e-commerce platform with user validation at every step.

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL (to persist data in database)

## Modules

* Login, Logout Module
* Seller Module
* Customer Module
* Product Module
* Cart Module
* Order Module

## Features

* Customer and Seller authentication & validation with session token having validity of 1 hour for security purposes
* Seller Features:
    * Administrator Role of the entire application
    * Only registered seller with valid session token can add/update/delete products from main database
    * Seller can access the details of different customers, orders
* Customer Features:
    * Registering themselves with application, and logging in to get the valid session token
    * Viewing different products and adding them to cart and placing orders
    * Only logged in user can access his orders, cart and other features.

## Contributors

* [@abinashpanigrahi](https://github.com/abinashpanigrahi)
* [@Dathuram16](https://github.com/Dathuram16)
* [@kamalvinjamoori](https://github.com/kamalvinjamoori)
* [@anandrajsingh05](https://github.com/anandrajsingh05)
* [@Adithyanathkv](https://github.com/Adithyanathkv)


## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](E-Commerce-Backend\src\main\resources\application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8009

    spring.datasource.url=jdbc:mysql://localhost:3306/ecommercedb
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```
