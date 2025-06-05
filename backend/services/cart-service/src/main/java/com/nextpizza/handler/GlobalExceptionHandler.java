package com.nextpizza.handler;

import com.nextpizza.dto.ErrorResponseDto;
import com.nextpizza.exception.CartItemNotFoundException;
import com.nextpizza.exception.CartNotFoundException;
import com.nextpizza.exception.CartTokenNotPresentException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;

import static java.lang.String.format;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFound(
            CartItemNotFoundException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("Product not found: {} | Path: {}", ex.getMessage(), pathInfo, ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Resource Not Found",
                ex.getMessage(),
                List.of(pathInfo)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFound(
            CartNotFoundException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("Product not found: {} | Path: {}", ex.getMessage(), pathInfo, ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Resource Not Found",
                ex.getMessage(),
                List.of(pathInfo)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(CartTokenNotPresentException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFound(
            CartTokenNotPresentException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("Product not found: {} | Path: {}", ex.getMessage(), pathInfo, ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Resource Not Found",
                ex.getMessage(),
                List.of(pathInfo)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(this::formatValidationError)
                .toList();

        log.warn("Validation failed: {} | Path: {} | Errors: {}",
                "Request contains invalid fields",
                pathInfo,
                errors,
                ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Validation Failed",
                "Request contains invalid fields",
                errors
        );

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAll(
            Exception ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("Unexpected error occurred | Path: {} | Error: {}",
                pathInfo,
                ex.getMessage(),
                ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Internal Server Error",
                "An unexpected error occurred",
                List.of(pathInfo, ex.getMessage())
        );

        return ResponseEntity
                .internalServerError()
                .body(response);
    }

    private String formatValidationError(FieldError error) {
        return format("%s: %s",
                error.getField(),
                error.getDefaultMessage());
    }

    private String getPathInfo(HttpServletRequest request) {
        return format("%s %s",
                request.getMethod(),
                request.getRequestURI());
    }

}
