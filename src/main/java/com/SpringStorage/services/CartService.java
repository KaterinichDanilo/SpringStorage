package com.SpringStorage.services;

import com.SpringStorage.entities.Clothes;
import com.SpringStorage.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CartService {
    private Cart cart;
    private ClothesService clothesService;

    public CartService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @PostConstruct
    private void init(){
        this.cart = new Cart();
    }

    public Cart getNewCart() {
        return new Cart();
    }

    public Cart getCart(){
        return cart;
    }

    public void add(Long id){
        Clothes clothes = clothesService.findById(id).orElseThrow(() -> new RuntimeException("Clothes not found id = " + id));
        this.cart.add(clothes);
    }

    public void remove(Long prodId){
        cart.remove(prodId);
    }

    public void clear(){
        cart.clear();
    }
}
