package com.SpringStorage.models;

import com.SpringStorage.entities.Clothes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartItem> cart;
    private int totalPrice;

    public Cart() {
        this.cart = new ArrayList<>();
    }

    public List<CartItem> getCart() {
        return Collections.unmodifiableList(cart);
    }

    private void recalculatePrice(){
        this.totalPrice = 0;
        for (CartItem item : this.cart) {
            this.totalPrice += item.getTotalPrice();
        }
    }

    public void add(Clothes clothes){
        for (CartItem item : cart) {
            if (item.getProdId().equals(clothes.getId())){
                item.changeQuantity(1);
                recalculatePrice();
                return;
            }
        }

        cart.add(new CartItem(clothes.getId(), clothes.getTitle(), 1,
                clothes.getPrice(), clothes.getPrice()));
        recalculatePrice();
    }

    public void increase(Long prodId, int delta){
        CartItem cartItem = cart.stream().filter(i -> i.getProdId().equals(prodId)).findAny().
                orElseThrow(() -> new RuntimeException("Product wasn't found in cart id = " + prodId));
        cartItem.changeQuantity(delta);
        recalculatePrice();
    }

    public void reduce(Long prodId, int delta){
        CartItem cartItem = cart.stream().filter(i -> i.getProdId().equals(prodId)).findAny().
                orElseThrow(() -> new RuntimeException("Product wasn't found in cart id = " + prodId));
        if (cartItem.getQuantity() <= Math.abs(delta)){
            remove(cartItem.getProdId());
            recalculatePrice();
            return;
        }
        cartItem.changeQuantity(delta);
        recalculatePrice();
    }

    public void remove(Long id){
        if(cart.removeIf(cartItem -> cartItem.getProdId().equals(id))) recalculatePrice();
    }

    public void clear(){
        cart.clear();
        totalPrice = 0;
    }
}
