package com.statemachine.statemachine.order.services;

import com.statemachine.statemachine.order.entities.Order;
import com.statemachine.statemachine.order.entities.OrderState;
import com.statemachine.statemachine.order.repositories.OrdersRepository;
import com.statemachine.statemachine.user.entities.User;
import com.statemachine.statemachine.user.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderStateService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private RiderService riderService;

    public Order setAccept(Order order) throws Exception {
        if(order == null) throw new Exception("Order is null");
        if(order.getOrderState() != OrderState.CREATED) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRestaurant().getId() != user.getId()) throw new Exception("This is not your order");
        order.setOrderState(OrderState.ACCEPTED);
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(user);
        return ordersRepository.save(order);
    }

    public Order setInPreparation(Order order) throws Exception{
        if(order == null) throw new Exception("Order is null");
        if(order.getOrderState() != OrderState.ACCEPTED) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRestaurant().getId() != user.getId()) throw new Exception("This is not your order");
        order.setOrderState(OrderState.IN_PREPARATION);
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(user);
        return ordersRepository.save(order);
    }

    public Order setReady(Order order) throws Exception{
        if(order == null) throw new Exception("Order is null");
        if(order.getOrderState() != OrderState.IN_PREPARATION) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRestaurant().getId() != user.getId()) throw new Exception("This is not your order");
        order.setOrderState(OrderState.READY);
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(user);
        return ordersRepository.save(order);
    }

    public Order setDelivering(Order order) throws Exception{
        if(order == null) throw new Exception("Order is null");
        if(order.getOrderState() != OrderState.READY) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRider().getId() != user.getId()) throw new Exception("This is not your order");
        order.setOrderState(OrderState.DELIVERING);
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(user);
       return ordersRepository.save(order);
    }

    public Order setCompleted(Order order) throws Exception{
        if(order == null) throw new Exception("Order is null");
        if(order.getOrderState() != OrderState.READY) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRider().getId() != user.getId()) throw new Exception("This is not your order");
        order.setOrderState(OrderState.COMPLETED);
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(user);
        return ordersRepository.save(order);
    }

    public Optional<Order> findById(long id) {
        return ordersRepository.findById(id);
    }
}
