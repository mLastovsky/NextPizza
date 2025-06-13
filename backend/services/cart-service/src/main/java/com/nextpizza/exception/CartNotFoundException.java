package com.nextpizza.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CartNotFoundException extends RuntimeException {

    private final String message;

}
