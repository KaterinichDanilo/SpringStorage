package com.SpringStorage.services;

import com.SpringStorage.entities.Clothes;
import com.SpringStorage.models.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private Cart cart;
    @Autowired
    ClothesService clothesService;

    @PostConstruct
    private void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void add(Long id){
        Clothes clothes = clothesService.findById(id).orElseThrow(() -> new RuntimeException("Clothes not found id = " + id));
        cart.add(clothes);
    }

    public void remove(Long prodId){
        cart.remove(prodId);
    }

    public void clear(){
        cart.clear();
    }
}
