# 🛒 EasyShop E-Commerce API

::: {align="center"}
## Secure RESTful E-Commerce Application

Built with **Java • Spring Boot • Spring Security • Spring Data JPA •
MySQL**

![Java](https://img.shields.io/badge/Java-17-red?style=for-the-badge)
![Spring
Boot](https://img.shields.io/badge/Spring_Boot-Framework-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring
Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
:::

------------------------------------------------------------------------

## 📑 Table of Contents

-   Project Overview
-   Technologies
-   Project Structure
-   Features
-   Screenshots
-   Interesting Code
-   Running the Application
-   Author

------------------------------------------------------------------------

# 📖 Project Overview

EasyShop is a RESTful e-commerce application developed using Java,
Spring Boot, Spring Security, Spring Data JPA, and MySQL. Users can
browse products, search by filters, manage a shopping cart, update their
profile, and securely complete checkout. Administrators can manage
categories through protected endpoints.

# 🚀 Technologies

  Technology        Purpose
  ----------------- ----------------------
  Java 17           Programming Language
  Spring Boot       REST API
  Spring Security   Authentication
  JWT               Secure Login
  Spring Data JPA   Database Access
  Hibernate         ORM
  MySQL             Database
  Maven             Build Tool
  Insomnia          API Testing
  Git/GitHub        Version Control

# 🏗️ Project Structure

## 🎮 Controllers

-   AuthenticationController
-   CategoriesController
-   ProductsController
-   ShoppingCartController
-   ProfileController
-   OrderController

## ⚙️ Services

-   AuthenticationService
-   UserService
-   CategoryService
-   ProductService
-   ShoppingCartService
-   ProfileService
-   OrderService

## 🗄️ Repositories

-   UserRepository
-   CategoryRepository
-   ProductRepository
-   ShoppingCartRepository
-   ProfileRepository
-   OrderRepository
-   OrderLineItemRepository

## 📦 Models

-   User
-   Profile
-   Authority
-   LoginDto
-   LoginResponseDto
-   RegisterUserDto
-   Product
-   Category
-   ShoppingCart
-   ShoppingCartItem
-   CartItem
-   Order
-   OrderLineItem

## 🔐 Security

-   WebSecurityConfig
-   JWTFilter
-   TokenProvider
-   JwtAuthenticationEntryPoint
-   JwtAccessDeniedHandler

# 🏛️ Architecture

``` text
Client (Insomnia)
       │
       ▼
 Controllers
       │
       ▼
   Services
       │
       ▼
Repositories
       │
       ▼
 MySQL Database
```

# ✨ Features

-   🛍️ Browse and search products
-   📂 View and manage categories
-   🛒 Add, update, remove and clear shopping cart items
-   👤 View and update user profile
-   💳 Secure checkout
-   📦 Automatically create orders and order line items

# 📸 Application Screenshots

## 🛍️ Products

![Products](images/products.png)

## 📂 Categories

![Categories](images/categories.png)

## 🛒 Shopping Cart

![Cart](images/cart.png)

## 👤 Profile

![Profile](images/profile.png)

## 💳 Checkout

![Checkout](images/checkout.png)

# ⭐ Interesting Code

The checkout feature is the heart of the application. It combines the
shopping cart, user profile, products, orders, and order line items into
a complete purchase workflow.

``` java
for (CartItem cartItem : cartItems) {

    Product product = productService.getById(cartItem.getProductId());

    OrderLineItem lineItem = new OrderLineItem();
    lineItem.setOrderId(savedOrder.getOrderId());
    lineItem.setProductId(cartItem.getProductId());
    lineItem.setQuantity(cartItem.getQuantity());
    lineItem.setSalesPrice(product.getPrice());
    lineItem.setDiscount(BigDecimal.ZERO);

    orderLineItemRepository.save(lineItem);
}

shoppingCartRepository.deleteByUserId(userId);
```

### Why this code?

This logic converts every item in the user's shopping cart into an order
line item, stores it in the database, and clears the cart after a
successful checkout. It demonstrates how multiple layers of the
application work together to complete an e-commerce transaction.

# ▶️ Running the Application

1.  Clone the repository.
2.  Create the EasyShop MySQL database.
3.  Configure `application.properties`.
4.  Run the Spring Boot application.
5.  Test the API using Insomnia.

# 👨‍💻 Author

**Lovi Tesfay**

Java Developer • Spring Boot • REST APIs • MySQL
