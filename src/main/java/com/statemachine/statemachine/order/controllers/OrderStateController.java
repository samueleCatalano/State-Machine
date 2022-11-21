package com.statemachine.statemachine.order.controllers;

import com.statemachine.statemachine.order.entities.Order;
import com.statemachine.statemachine.order.repositories.OrdersRepository;
import com.statemachine.statemachine.order.services.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/orders/{id}/state")
public class OrderStateController {

    @Autowired
    private OrderStateService orderStateService;

    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PutMapping("/accepted")
    public ResponseEntity accept(@PathVariable long id) throws Exception {
        Optional<Order> order = orderStateService.findById(id);
        if(!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setAccept(order.get()));
    }

    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PutMapping("/preparation")
    public ResponseEntity inPreparation(@PathVariable long id) throws Exception {
        Optional<Order> order = orderStateService.findById(id);
        if(!order.isPresent()) return ResponseEntity.notFound().build();
       return ResponseEntity.ok( orderStateService.setInPreparation(order.get()));
    }

    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PutMapping("/ready")
    public ResponseEntity ready(@PathVariable long id) throws Exception{
        Optional<Order> order = orderStateService.findById(id);
        if(!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setReady(order.get()));
    }

    @PreAuthorize("hasRole('ROLE_RIDER')")
    @PutMapping("/delivering")
    public ResponseEntity delivering(@PathVariable long id) throws Exception{
        Optional<Order> order = orderStateService.findById(id);
        if(!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setDelivering(order.get()));
    }

    @PreAuthorize("hasRole('ROLE_RIDER')")
    @PutMapping("/completed")
    public ResponseEntity completed(@PathVariable long id) throws Exception{
        Optional<Order> order = orderStateService.findById(id);
        if(!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setDelivering(order.get()));
    }
}
