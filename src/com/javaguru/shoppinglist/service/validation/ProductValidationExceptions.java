package com.javaguru.shoppinglist.service.validation;

public class ProductValidationExceptions extends RuntimeException {
    public ProductValidationExceptions(String message) {
        super(message);
    }
}