package com.statemachine.statemachine.user.repositories;

import com.statemachine.statemachine.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByActivationCode(String activationCode);

    User findByPasswordResetCode(String passwordResetCode);

    @Query(nativeQuery = true, value = """
            SELECT *\s
            FROM (
            \tSELECT u.*, COUNT(busyOrders.id) AS numberOfOrders
            \tFROM `user` AS u
            \tLEFT JOIN user_roles AS ur ON ur.user_id = u.id\s
            \tLEFT JOIN ( SELECT * FROM `orders` WHERE `status` IN(4) ) AS busyOrders ON busyOrders.rider_id = u.id
            \tWHERE ur.role_id = 2  AND u.is_active = 1
            \tGROUP BY u.id
            ) AS allRiders\s
            WHERE allRiders.numberOfOrders = 0\s
            LIMIT 1""")
    Optional<User> pickRider();
}
