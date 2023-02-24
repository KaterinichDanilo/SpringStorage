package com.SpringStorage.controllers;

import com.SpringStorage.models.Cart;
import com.SpringStorage.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable(name = "id") Long id){
        cartService.add(id);
    }

    @GetMapping
    public Cart getCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/inc/{id}")
    @ResponseBody
    public Cart increase(@RequestBody Cart cart, @PathVariable(name = "id") Long id){
        cart.increase(id, 1);
        return cart;
    }

    @GetMapping("/reduce/{id}")
    @ResponseBody
    public Cart reduce(@RequestBody Cart cart, @PathVariable(name = "id") Long id){
        cart.reduce(id, -1);
        return cart;
    }

    @GetMapping("/clear")
    public void clear(){
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public Cart removeFromCart(@RequestBody Cart cart, @PathVariable(name = "id") Long id){
        cartService.remove(id);
        return cart;
    }
}
