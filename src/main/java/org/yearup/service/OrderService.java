package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.models.*;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;
import org.yearup.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;
    private final ProfileService profileService;

    /// Constructor
    public OrderService(OrderRepository orderRepository,
                        OrderLineItemRepository orderLineItemRepository,
                        ShoppingCartRepository shoppingCartRepository,
                        ProductService productService,
                        ProfileService profileService) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.profileService = profileService;
    }

    /// Checkout
    public Order checkout(int userId) {
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

        Profile profile = profileService.getByUserId(userId);
        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());


        // Shipping information
        order.setAddress(profile.getAddress());
        order.setCity(profile.getCity());
        order.setState(profile.getState());
        order.setZip(profile.getZip());

        order.setShippingAmount(BigDecimal.ZERO);

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
