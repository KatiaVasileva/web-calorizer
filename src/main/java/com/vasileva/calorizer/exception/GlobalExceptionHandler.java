package com.vasileva.calorizer.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FoodNotFoundException.class)
    public ErrorResponse handleFoodNotFoundException(FoodNotFoundException e) {
        logger.error("Food not found exception: {}", e.getMessage());
        return new ErrorResponse("Bad Request", e.getMessage());
    }

    @ExceptionHandler({FoodExistsException.class})
    public ErrorResponse handleFoodExistsException(FoodExistsException e) {
        logger.error("Food exists exception: {}", e.getMessage());
        return new ErrorResponse("Bad Request", e.getMessage());
    }
}
