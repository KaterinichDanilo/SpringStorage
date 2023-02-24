package com.SpringStorage.controllers;

import com.SpringStorage.models.Cart;
import com.SpringStorage.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/makeorder/{login}")
    public void createOrder(@RequestBody Cart cart, @RequestParam String login){
        orderService.makeOrder(cart, login);
    }
}
