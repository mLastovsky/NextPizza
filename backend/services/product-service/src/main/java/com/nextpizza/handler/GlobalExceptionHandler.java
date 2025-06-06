package com.nextpizza.handler;

import com.nextpizza.exception.CategoryNotFoundException;
import com.nextpizza.exception.IngredientNotFoundException;
import com.nextpizza.exception.ProductItemNotFoundException;
import com.nextpizza.exception.ProductNotFoundException;
import com.nextpizza.dto.ErrorResponseDto;
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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFound(
            ProductNotFoundException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("Product not found: {} | Path: {}", ex.getMsg(), pathInfo, ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Resource Not Found",
                ex.getMsg(),
                List.of(pathInfo)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleIngredientNotFound(
            IngredientNotFoundException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("Ingredient not found: {} | Path: {}", ex.getMsg(), pathInfo, ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Resource Not Found",
                ex.getMsg(),
                List.of(pathInfo)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCategoryNotFound(
            CategoryNotFoundException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("Category not found: {} | Path: {}", ex.getMsg(), pathInfo, ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Resource Not Found",
                ex.getMsg(),
                List.of(pathInfo)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(ProductItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductItemNotFound(
            ProductItemNotFoundException ex,
            HttpServletRequest request
    ) {
        var pathInfo = getPathInfo(request);
        log.error("ProductItem not found: {} | Path: {}", ex.getMsg(), pathInfo, ex);

        var response = new ErrorResponseDto(
                OffsetDateTime.now(),
                "Resource Not Found",
                ex.getMsg(),
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
