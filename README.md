# REST API for an E-Commerce Application

* We have developed this REST API for an e-commerce application. This API performs all the fundamental CRUD operations of any e-commerce platform with user validation at every step.
* This project is developed by a team of 5 members during our project week in Masai School, Bengaluru. 


## E-R Diagram for the application

![E-R Diagram](./ER%20Diagram/E-Commerce%20API%20ER%20Diagram.jpeg?raw=true)

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL

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

## API Root Endpoint

`https://localhost:8009/`

`http://localhost:8009/swagger-ui/index.html#/`


## API Module Endpoints

### Login & Logout Module

* `POST /register/customer` : Register a new customer
* `POST /login/customer` : Logging in customer with valid mobile number & password
* `POST /logout/customer` : Logging out customer based on session token
* `POST /register/seller` : Register a new seller
* `POST /login/seller` : Logging in Seller
* `POST /logout/seller` : Logging out Seller based on session token


### Customer Module

* `GET /customer/current` : Getting currently logged in customer
* `GET /customer/orders` : Getting order history of logged in customer
* `GET /customers` : Getting All customers
* `PUT /customer` : Updates logged in customer
* `PUT /customer/update/password` : Updates customer password
* `PUT /customer/update/card` : Updates credit card details
* `PUT /customer/update/address?type=home` : Updates customer's home address
* `PUT /customer/update/credentials` : Updates email address and mobile number
* `DELETE /customer` : Deletes logged in user with valid session token
* `DELETE /customer/delete/address?type=home` : Deletes customer's home address


### Seller Module

* `GET /seller/{sellerid}` : Gets seller with passed seller Id
* `GET /seller/current` : Gets seller details for currently logged in seller
* `GET /sellers` : Gets all sellers
* `POST /addseller` : Adding new seller
* `PUT /seller` : Updates seller details
* `PUT /seller/update/password` : Updates seller password
* `PUT /seller/update/mobile` : Updates seller mobile number
* `DELETE /seller/{sellerid}` : Deletes seller with passed id


### Product Module

* `GET /product/{id}` : Gets product with given product id
* `GET /products` : Gets all products
* `GET /products/{category}` : Gets product with given category
* `POST /products` : Adds a new product to database
* `PUT /products` : Updates the product with given product id
* `PUT /products/{id}` : Updates product quantity
* `DELETE /product/{id}` : Deletes product with given id


### Cart Module

* `GET /cart` : Get all items in Customer Cart
* `POST /cart/add` : Add item to Cart
* `DELETE /cart` : Remove item from Cart
* `DELETE /cart/clear` : Clear entire cart


### Order Module

* `GET /orders/{id}` : Gets order details with given order id
* `GET /orders` : Gets all orders
* `GET /orders/by/date` : Gets orders placed on given date (DD-MM-YYYY)
* `POST /order/place` : Places a new order based on cart items
* `PUT /orders/{id}` : Updates a pending order
* `DELETE /orders/{id}` : Cancels an order


### Sample API Response for Customer Login

`POST   localhost:8009/login/customer`

* Request Body

```
    {
        "mobileId": "9999999999",
        "password": "shyam123456"
    }
```

* Response

```
    {
        "sessionId": 23,
        "token": "customer_0ad57094",
        "userId": 19,
        "userType": "customer",
        "sessionStartTime": "2022-06-10T10:48:20.0109626",
        "sessionEndTime": "2022-06-10T11:48:20.0109626"
    }
```
