package com.javaguru.shoppinglist.service.validation;

public class ValidationExceptionMessages {
    public static final String CATEGORY_VALIDATION_MESSAGE = "Product category must be not null";
    public static final String DESCRIPTION_VALIDATION_MESSAGE = "Product description must be not empty";
    public static final String DISCOUNT_VALIDATION_MESSAGE = "Product discount is Incorrect, (Correct bounds (0 ~ 100.0))";
    public static final String EMPTY_NAME_VALIDATION_MESSAGE = "Product name must be not empty";
    public static final String INCORRECT_NAME_LENGTH_VALIDATION_MESSAGE = "Product name length is incorrect (3 - 32 letters)";
    public static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found";
    public static final String PRICE_VALIDATION_MESSAGE = "Product price is Incorrect, price must be more than ZERO";
    public static final String UNIQUE_NAME_VALIDATION_MESSAGE = "Product name must be unique";
    public static final String PRODUCT_VALIDATION_MESSAGE = "Product must be not null";

    private ValidationExceptionMessages() {
    }
}
