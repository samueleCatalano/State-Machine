package com.statemachine.statemachine.order.repositories;

import com.statemachine.statemachine.order.entities.Order;
import com.statemachine.statemachine.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreatedBy(User user);

    Object findByRestaurant(User user);

    Object findByRider(User user);
}
