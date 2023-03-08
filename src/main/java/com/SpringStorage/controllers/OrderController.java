package com.SpringStorage.controllers;

import com.SpringStorage.models.Cart;
import com.SpringStorage.services.CartService;
import com.SpringStorage.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;


    @PostMapping("/makeorder/{login}")
    public void createOrder(@RequestParam String login){
        orderService.makeOrder(cartService.getCart(), login);
    }
}
