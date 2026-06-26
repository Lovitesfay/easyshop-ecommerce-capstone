package org.yearup.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.yearup.models.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.models.User;
import org.yearup.service.OrderService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
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
