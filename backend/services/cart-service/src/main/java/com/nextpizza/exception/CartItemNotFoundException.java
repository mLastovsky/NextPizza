package com.nextpizza.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CartItemNotFoundException extends RuntimeException {

    private final String message;

}
