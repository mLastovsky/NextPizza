package com.nextpizza.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CartTokenNotPresentException extends RuntimeException {

    private final String message;

}
