package org.yearup.controllers;

import org.springframework.web.bind.annotation.*;
import org.yearup.models.*;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

//// convert this class to a REST controller
//// only logged in users should have access to these actions

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class ShoppingCartController
{
    //// a shopping cart controller depends on the service layer
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController
            (ShoppingCartService shoppingCartService,
             UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }


   ////added GetMapping // each method in this controller requires a Principal object as a parameter
    @GetMapping
    public ShoppingCart getUserById(Principal principal)
    {
        //// get the currently logged in username
        String userName = principal.getName();
        //// find database user by username
        User user = userService.getByUserName(userName);
        int userId = user.getId();

      ////changed return  // use the shoppingCartService to get all items in the cart and return the cart
        return shoppingCartService.getByUserId(userId);
    }

    /// add a POST method to add a product to the cart - the url should be
    /// https://localhost:8080/cart/products/15  (15 is the productId to be added)
    @PostMapping("/products/{productId}")
    /// I UPDATED LINE 50 - 58
    public ShoppingCart addItem(@PathVariable int productId, Principal principal)
    {
        String userName = principal.getName();

        User user = userService.getByUserName(userName);

        int userId = user.getId();

        return shoppingCartService.addItem(userId, productId);
    }


    //// add a PUT method to update an existing product in the cart - the url should be
    //// https://localhost:8080/cart/products/15  (15 is the productId to be updated)
    //// the BODY should be a ShoppingCartItem - quantity is the only value that will be updated; return the cart (200 OK)

    @PutMapping("/products/{productId}")
    public ShoppingCart updateItem(
            @PathVariable int productId,
            @RequestBody ShoppingCartItem item,
            Principal principal)
    {
        String userName = principal.getName();

        User user = userService.getByUserName(userName);

        return shoppingCartService.updateItem(
                user.getId(),
                productId,
                item.getQuantity());
    }


    //// add a DELETE method to clear all products from the current users cart
    //// https://localhost:8080/cart  - return the (now empty) cart so the front end can refresh it (200 OK)
    /// added lines 83-91


    /// I added this ... delete one product from cart
    @DeleteMapping("/products/{productId}")
    public void removeProduct(@PathVariable int productId,
                              Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        shoppingCartService.removeProduct(user.getId(), productId);
    }

    @DeleteMapping
    public ShoppingCart clearCart(Principal principal)
    {
        String userName = principal.getName();

        User user = userService.getByUserName(userName);

        return shoppingCartService.clearCart(user.getId());
    }
}
