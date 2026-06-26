package org.yearup.controllers;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.yearup.models.User;
import org.yearup.service.OrderService;
import org.yearup.service.UserService;

import java.security.Principal;

public class OrderController {

    ///  made this fields
    private final OrderService orderService;
    private final UserService userService;

    /// Generated a Constructor
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;

    }

    /// @PostMapping Checkout method
    @PostMapping
    public Order checkout(Principal principal)
    {
        String username = principal.getName();
        User user = userService.getByUserName(username);

        return (Order) orderService.checkout(user.getId());
    }
}
