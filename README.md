# 🛒 EasyShop E-Commerce API

A RESTful e-commerce application built with **Java**, **Spring Boot**,
**Spring Security**, **Spring Data JPA**, and **MySQL**.

EasyShop allows users to browse products, view categories, manage a
shopping cart, update their profile, and complete checkout by creating
an order.

------------------------------------------------------------------------

## 🛠 Technologies Used

-   Java 17
-   Spring Boot
-   Spring Security / JWT
-   Spring Data JPA
-   Hibernate
-   MySQL
-   Maven
-   Insomnia
-   Git & GitHub

------------------------------------------------------------------------

## ✨ Features

-   View and search products
-   View product categories
-   Add products to a shopping cart
-   Update cart quantities
-   Clear the shopping cart
-   View and update user profile
-   Checkout and create orders
-   Save order line items
-   Admin-only category management

------------------------------------------------------------------------

## 📸 Application Screenshots

### Products

```{=html}
<p align="center">
```
`<img src="images/products.png" width="1000">`{=html}
```{=html}
</p>
```

------------------------------------------------------------------------

### Categories

```{=html}
<p align="center">
```
`<img src="images/categories.png" width="1000">`{=html}
```{=html}
</p>
```

------------------------------------------------------------------------

### Shopping Cart

```{=html}
<p align="center">
```
`<img src="images/cart.png" width="1000">`{=html}
```{=html}
</p>
```

------------------------------------------------------------------------

### User Profile

```{=html}
<p align="center">
```
`<img src="images/profile.png" width="1000">`{=html}
```{=html}
</p>
```

------------------------------------------------------------------------

### Checkout

```{=html}
<p align="center">
```
`<img src="images/checkout.png" width="1000">`{=html}
```{=html}
</p>
```

------------------------------------------------------------------------

## ⭐ Interesting Code

The checkout feature retrieves the current user's shopping cart, creates
an order, creates order line items for each product, and clears the
shopping cart after a successful checkout.

``` java
public Order checkout(int userId) {

    List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

    Profile profile = profileService.getByUserId(userId);

    Order order = new Order();
    order.setUserId(userId);
    order.setDate(LocalDateTime.now());
    order.setAddress(profile.getAddress());
    order.setCity(profile.getCity());
    order.setState(profile.getState());
    order.setZip(profile.getZip());
    order.setShippingAmount(BigDecimal.ZERO);

    Order savedOrder = orderRepository.save(order);

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

    return savedOrder;
}
```

### Why this code is interesting

This method connects multiple parts of the application together by
creating an order, saving every purchased product, copying the user's
shipping information, and clearing the shopping cart after checkout.

------------------------------------------------------------------------

## ▶️ How to Run

1.  Clone the repository.
2.  Create the EasyShop MySQL database.
3.  Update `application.properties`.
4.  Run the Spring Boot application.
5.  Test the API using Insomnia.

------------------------------------------------------------------------

## 👨‍💻 Author

**Lovi Tesfay**
