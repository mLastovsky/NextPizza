package com.nextpizza.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserNotCreatedException extends RuntimeException {

    private final String message;

}
