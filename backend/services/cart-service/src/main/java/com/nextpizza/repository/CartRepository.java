package com.nextpizza.repository;

import com.nextpizza.model.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @EntityGraph(attributePaths = {"cartItems"})
    Optional<Cart> getCartByToken(String token);

}
