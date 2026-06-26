package org.yearup.service;

import org.yearup.models.CartItem;
import org.yearup.models.Order;
import org.yearup.models.Product;
import org.yearup.repository.OrderRepository;
import org.yearup.repository.ShoppingCartRepository;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    // Constructor
    public OrderService(OrderRepository orderRepository,
                        ShoppingCartRepository shoppingCartRepository,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public Order checkout(int userId) {
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            Product product = productService.getById(cartItem.getProductId());


        }
        shoppingCartRepository.deleteByUserId(userId);
        return savedOrder;
    }

    }
