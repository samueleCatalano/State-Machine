package com.statemachine.statemachine.order.controllers;

import com.statemachine.statemachine.order.entities.Order;
import com.statemachine.statemachine.order.entities.OrderDTO;
import com.statemachine.statemachine.order.services.OrderService;
import com.statemachine.statemachine.user.entities.User;
import com.statemachine.statemachine.user.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_REGISTERED')")
    public Order create(@RequestBody OrderDTO order) throws Exception{
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasRole('"+ Roles.RESTAURANT +"') OR returnObject.body == null OR returnObject.body.createdBy.id == authentication.principal.id")
    public ResponseEntity<Order> getSingle(@PathVariable Long id, Principal principal){
        Optional<Order> order = orderService.findById(id);
        if(!order.isPresent()) return ResponseEntity.notFound().build();
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        if(Roles.hasRole(user,Roles.REGISTERED) && order.get().getCreatedBy().getId() == user.getId()){
            return ResponseEntity.ok(order.get());
        }else if(Roles.hasRole(user,Roles.RESTAURANT) && order.get().getRestaurant().getId() == user.getId()) {
            return ResponseEntity.ok(order.get());
        }else if(Roles.hasRole(user,Roles.RIDER) && order.get().getRider().getId() == user.getId()) {
            return ResponseEntity.ok(order.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(Roles.hasRole(user, Roles.REGISTERED)){
            return ResponseEntity.ok(orderService.findByCreatedBy(user));
        }else if(Roles.hasRole(user, Roles.RESTAURANT)){
            return ResponseEntity.ok(orderService.findByRestaurant(user));
        } else if (Roles.hasRole(user, Roles.RIDER)) {
            return ResponseEntity.ok(orderService.findByRider(user));
        }
        return null;
    }

}
