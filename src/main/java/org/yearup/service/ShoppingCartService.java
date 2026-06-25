package org.yearup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

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
        /// load the user's cart rows, look up each product, and build the ShoppingCart
       /// added line 28 to line 40
        List<CartItem> items = shoppingCartRepository.findByUserId(userId);

        ShoppingCart cart = new ShoppingCart();

        for(CartItem item : items)
        {
            Product product = productService.getById(item.getProductId());

            item.setProduct(product);

            cart.addItem(item);
        }
        return cart;
        ///changed return
    }

    /// add additional methods here
    /// ADD ITEM
    /// added line 47 to line 65
    public ShoppingCart addItem(int userId, int productId)
    {

        CartItem item = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        if(item == null)
        {
            item = new CartItem();
            item.setUserId(userId);
            item.setProductId(productId);
            item.setQuantity(1);
        }
        else
        {
            item.setQuantity(item.getQuantity() + 1);
        }

        shoppingCartRepository.save(item);
        return getByUserId(userId);
    }

    /// added line 69 - 87
    public ShoppingCart updateItem(int userId, int productId, int quantity)
    {
        CartItem item = shoppingCartRepository
                .findByUserIdAndProductId(userId, productId);

        //// Only update if the product already exists in the user's cart
        if(item != null)
        {
            if(quantity <= 0)
            {
                shoppingCartRepository.delete(item);
            }
            else
            {
                item.setQuantity(quantity);
                shoppingCartRepository.save(item);
            }
        }

        return getByUserId(userId);
    }

    /// added this to clear cart
    @Transactional
    public ShoppingCart clearCart(int userId)
    {
        shoppingCartRepository.deleteByUserId(userId);

        return new ShoppingCart();
    }



}
