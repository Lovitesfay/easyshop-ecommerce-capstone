package org.yearup.service;

import org.yearup.models.CartItem;
import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;
import org.yearup.models.Product;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;
import org.yearup.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    /// Constructor
    public OrderService(OrderRepository orderRepository,
                        OrderLineItemRepository orderLineItemRepository,
                        ShoppingCartRepository shoppingCartRepository,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    /// Checkout
    public Order checkout(int userId) {
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            Product product = productService.getById(cartItem.getProductId());

            /// I added This, this Lists the Products and with the info.
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setOrderId(savedOrder.getOrderId());
            lineItem.setProductId(cartItem.getProductId());
            lineItem.setQuantity(cartItem.getQuantity());
            lineItem.setSalesPrice(product.getPrice()); ///use product's price
            lineItem.setDiscount(BigDecimal.ZERO); ///default discount

            orderLineItemRepository.save(lineItem);

        }
        shoppingCartRepository.deleteByUserId(userId);
        return savedOrder;
    }

    }
