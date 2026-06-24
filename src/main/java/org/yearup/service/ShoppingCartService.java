package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;
import org.yearup.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {
        //// load the user's cart rows, look up each product, and build the ShoppingCart
        return shoppingCartRepository.findByUserId(userId);
        //changed return
    }

    // add additional methods here
    // ADD ITEM
    public ShoppingCart addItem(int userId, CartItem item)
    {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);

        cart.addItem(item);

        return cart;
    }

    s
}
