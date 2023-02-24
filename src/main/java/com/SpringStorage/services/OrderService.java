package com.SpringStorage.services;

import com.SpringStorage.entities.Order;
import com.SpringStorage.entities.OrderItem;
import com.SpringStorage.models.Cart;
import com.SpringStorage.repositories.OrderRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Data
public class OrderService {
    private OrderRepository orderRepository;
    private ClothesService clothesService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClothesService clothesService) {
        this.orderRepository = orderRepository;
        this.clothesService = clothesService;
    }

    @Transactional
    public void makeOrder(Cart cart, String login){
        Order order = new Order();
        order.setUsername(login);
        List<OrderItem> orderItems = cart.getCart().stream().map(i ->
                        new OrderItem(i.getQuantity(),
                                clothesService.findById(i.getProdId()).orElseThrow(() ->
                                        new RuntimeException("Clothes not found id = " + i.getProdId())), order)).
                collect(Collectors.toList());
        order.setOrderItems(orderItems);
        order.setStatus("Success");
        for (OrderItem i:orderItems) {
            i.setOrder(order);
        }
        orderRepository.save(order);
    }
}
