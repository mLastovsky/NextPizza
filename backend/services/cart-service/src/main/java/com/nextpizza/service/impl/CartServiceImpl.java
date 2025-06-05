package com.nextpizza.service.impl;

import com.nextpizza.dto.*;
import com.nextpizza.exception.CartNotFoundException;
import com.nextpizza.mapper.CartMapper;
import com.nextpizza.model.Cart;
import com.nextpizza.model.CartItem;
import com.nextpizza.model.IngredientCartItem;
import com.nextpizza.proxy.IngredientProxy;
import com.nextpizza.proxy.ProductItemProxy;
import com.nextpizza.repository.CartItemRepository;
import com.nextpizza.repository.CartRepository;
import com.nextpizza.repository.IngredientCartItemRepository;
import com.nextpizza.service.CartService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private static final int COOKIE_MAX_AGE_SECONDS = 86400;
    private static final String CART_TOKEN_COOKIE_NAME = "cartToken";

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;
    private final ProductItemProxy productItemProxy;
    private final IngredientProxy ingredientProxy;
    private final IngredientCartItemRepository ingredientCartItemRepository;

    @Override
    public CartResponseDto getCartByToken(String token) {
        if (isTokenEmpty(token)) {
            return createEmptyCartResponse();
        }

        var cart = findCartByTokenOrThrow(token);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDto addItemToCart(CreateCartItemDto dto, String token, HttpServletResponse response) {
        if (isTokenEmpty(token)) {
            token = generateNewToken();
            setCartTokenCookie(response, token);
        }

        var cart = findOrCreateCart(token);
        updateCartItems(cart, dto);
        calculateTotalAmount(cart);

        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDto calculateCartTotalAmount(String token) {
        if (isTokenEmpty(token)) {
            throw new CartNotFoundException("Cart token cannot be empty");
        }

        var cart = findCartByTokenOrThrow(token);
        calculateTotalAmount(cart);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    private boolean isTokenEmpty(String token) {
        return token == null || token.isEmpty();
    }

    private String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    private void setCartTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(CART_TOKEN_COOKIE_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_MAX_AGE_SECONDS);
        response.addCookie(cookie);
    }

    private CartResponseDto createEmptyCartResponse() {
        return new CartResponseDto(
                null,
                null,
                BigDecimal.ZERO,
                null,
                null,
                null,
                List.of()
        );
    }

    private Cart findCartByTokenOrThrow(String token) {
        return cartRepository.getCartByToken(token)
                .orElseThrow(() -> new CartNotFoundException(
                        format("Cart with token %s not found", token)
                ));
    }

    private Cart findOrCreateCart(String token) {
        return cartRepository.getCartByToken(token)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .token(token)
                                .totalAmount(BigDecimal.ZERO)
                                .build()
                ));
    }

    private void updateCartItems(Cart cart, CreateCartItemDto dto) {
        findMatchingCartItem(cart, dto)
                .ifPresentOrElse(
                        this::incrementItemQuantity,
                        () -> addNewCartItem(cart, dto)
                );
    }

    private Optional<CartItem> findMatchingCartItem(Cart cart, CreateCartItemDto dto) {
        return cart.getCartItems().stream()
                .filter(item -> matchesCartItem(item, dto))
                .findFirst();
    }

    private boolean matchesCartItem(CartItem item, CreateCartItemDto dto) {
        if (!item.getProductItemId().equals(dto.productItemId())) {
            return false;
        }

        var existingIds = cartItemRepository.findIngredientIdsByCartItemId(item.getId()).stream()
                .sorted()
                .toList();

        var requestedIds = dto.ingredients() != null
                ? dto.ingredients().stream().sorted().toList()
                : List.of();

        return existingIds.equals(requestedIds);
    }

    private void incrementItemQuantity(CartItem item) {
        item.setQuantity(item.getQuantity() + 1);
    }

    private void addNewCartItem(Cart cart, CreateCartItemDto dto) {
        var newItem = CartItem.builder()
                .cart(cart)
                .productItemId(dto.productItemId())
                .quantity(1L)
                .build();

        var savedItem = cartItemRepository.save(newItem);
        cart.getCartItems().add(savedItem);

        if (dto.ingredients() != null && !dto.ingredients().isEmpty()) {
            var itemIngredients = dto.ingredients().stream()
                    .map(ingredientId -> IngredientCartItem.builder()
                            .cartItem(savedItem)
                            .ingredientId(ingredientId)
                            .build())
                    .toList();

            ingredientCartItemRepository.saveAll(itemIngredients);
        }
    }

    private void calculateTotalAmount(Cart cart) {
        var total = cart.getCartItems().stream()
                .map(this::calculateItemTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalAmount(total);
    }

    private BigDecimal calculateItemTotalPrice(CartItem item) {
        var basePrice = productItemProxy.getProductPriceById(item.getProductItemId());

        var ingredientIds = cartItemRepository.findIngredientIdsByCartItemId(item.getId());

        var ingredientsPrice = ingredientIds.stream()
                .map(ingredientProxy::getIngredientPriceById)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return basePrice.add(ingredientsPrice)
                .multiply(BigDecimal.valueOf(item.getQuantity()));
    }

}
