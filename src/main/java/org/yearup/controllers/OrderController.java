package org.yearup.controllers;

import org.yearup.service.OrderService;
import org.yearup.service.UserService;

public class OrderController {

    ///  made this fields
    private final OrderService orderService;
    private final UserService userService;

    /// Generated a Constructor
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;

    }
}
