package com.nextpizza.mapper;

import com.nextpizza.dto.CartItemResponseDto;
import com.nextpizza.dto.CartResponseDto;
import com.nextpizza.model.Cart;
import com.nextpizza.model.CartItem;
import com.nextpizza.proxy.IngredientProxy;
import com.nextpizza.proxy.ProductItemProxy;
import com.nextpizza.proxy.UserProxy;
import com.nextpizza.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final ProductItemProxy productItemProxy;
    private final IngredientProxy ingredientProxy;
    private final UserProxy userProxy;
    private final CartItemRepository cartItemRepository;

    public CartResponseDto toDto(Cart cart) {
        var cartItemsDto = cart.getCartItems().stream()
                .map(this::toCartItemResponseDto)
                .collect(Collectors.toList());

        var userDto = cart.getUserId() != null ? userProxy.getUserById(cart.getUserId()) : null;

        return new CartResponseDto(
                cart.getId(),
                userDto,
                cart.getTotalAmount(),
                cart.getToken(),
                cart.getCreatedAt(),
                cart.getUpdatedAt(),
                cartItemsDto
        );
    }

    private CartItemResponseDto toCartItemResponseDto(CartItem cartItem) {
        var productItemDto = productItemProxy.getProductItemById(cartItem.getProductItemId());
        var ingredientIds = cartItemRepository.findIngredientIdsByCartItemId(cartItem.getId());
        var ingredientsDto = ingredientProxy.getIngredientsByIds(ingredientIds);

        return new CartItemResponseDto(
                cartItem.getId(),
                productItemDto,
                cartItem.getQuantity(),
                cartItem.getCreatedAt(),
                cartItem.getUpdatedAt(),
                ingredientsDto
        );
    }

}
