package com.statemachine.statemachine.order.services;

import com.statemachine.statemachine.order.entities.Order;
import com.statemachine.statemachine.order.entities.OrderDTO;
import com.statemachine.statemachine.order.repositories.OrdersRepository;
import com.statemachine.statemachine.user.entities.User;
import com.statemachine.statemachine.user.repositories.UserRepository;
import com.statemachine.statemachine.user.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public Order save(OrderDTO orderInput) throws Exception{
        if(orderInput == null) return null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy(user);
        order.setAddress(orderInput.getAddress());
        order.setCity(orderInput.getCity());
        order.setDescription(orderInput.getDescription());
        order.setState(orderInput.getState());
        order.setZipCode(orderInput.getZipCode());
        order.setNumber(orderInput.getNumber());
        order.setTotalPrice(orderInput.getTotalPrice());
        Optional<User> restaurant = userRepository.findById(orderInput.getRestaurantDTO());
        if(!restaurant.isPresent() || !Roles.hasRole(restaurant.get(), Roles.RESTAURANT)) throw new Exception("Restaurant not found");
        order.setRestaurant(restaurant.get());
        return orderRepository.save(order);
    }

    public Order update(Long id, Order orderInput){
        if(orderInput == null) return null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderInput.setId(id);
        orderInput.setUpdatedAt(LocalDateTime.now());
        orderInput.setUpdatedBy(user);
        return orderRepository.save(orderInput);
    }

    public boolean canEdit(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()) return false;
        return order.get().getCreatedBy().getId() == user.getId();
    }


    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }



    public Object findByCreatedBy(User user) {
        return orderRepository.findByCreatedBy(user);
    }


    public Object findByRestaurant(User user) {
        return orderRepository.findByRestaurant(user);
    }

    public Object findByRider(User user) {
        return orderRepository.findByRider(user);
    }
}
