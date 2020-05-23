package com.javaguru.shoppinglist.service.validation;

public class ProductNotRemovedException extends RuntimeException {
    public ProductNotRemovedException(String message) {
        super(message);
    }
}