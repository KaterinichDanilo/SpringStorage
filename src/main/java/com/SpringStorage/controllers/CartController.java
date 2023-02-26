package com.SpringStorage.controllers;

import com.SpringStorage.models.Cart;
import com.SpringStorage.services.CartService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/add/{id}")
    public Cart addToCart(@PathVariable(name = "id") Long id){
        cartService.add(id);
        return cartService.getCart();
    }

    @GetMapping("/new")
    public Cart getNewCart(){
        return cartService.getNewCart();
    }

    @GetMapping("/inc/{id}")
    public Cart increase(@PathVariable(name = "id") Long id){
        cartService.getCart().increase(id, 1);
        return cartService.getCart();
    }

    @GetMapping("/reduce/{id}")
    public Cart reduce(@PathVariable(name = "id") Long id){
        cartService.getCart().increase(id, -1);
        return cartService.getCart();
    }

    @GetMapping("/clear")
    public void clear(){
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public Cart removeFromCart(@PathVariable(name = "id") Long id){
        cartService.remove(id);
        return cartService.getCart();
    }
}
