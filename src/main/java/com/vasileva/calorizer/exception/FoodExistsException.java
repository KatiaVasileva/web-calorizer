package com.vasileva.calorizer.exception;

public class FoodExistsException extends RuntimeException {
    public FoodExistsException(String message) {
        super(message);
    }
}
