package com.nextpizza.repository;

import com.nextpizza.model.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @EntityGraph(attributePaths = {"cartItems"})
    Optional<Cart> getCartByToken(String token);

}
