package com.javaguru.shoppinglist.service.validation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidationExceptionMessagesTest {

    @Test
    public void shouldContainsProductCategoryValidationMessage() {
        String expected = "Product category must be not null";
        assertEquals(expected, ValidationExceptionMessages.CATEGORY_VALIDATION_MESSAGE);
    }

    @Test
    public void shouldContainsDescriptionValidationMessage() {
        String expected = "Product description must be not empty";
        assertEquals(expected, ValidationExceptionMessages.DESCRIPTION_VALIDATION_MESSAGE);
    }

    @Test
    public void shouldContainsDiscountValidationMessage() {
        String expected = "Product discount is Incorrect, (Correct bounds (0 ~ 100.0))";
        assertEquals(expected, ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
    }

    @Test
    public void shouldContainsEmptyNameValidationMessage() {
        String expected = "Product name must be not empty";
        assertEquals(expected, ValidationExceptionMessages.EMPTY_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void shouldContainsIncorrectNameLengthValidationMessage() {
        String expected = "Product name length is incorrect (3 - 32 letters)";
        assertEquals(expected, ValidationExceptionMessages.INCORRECT_NAME_LENGTH_VALIDATION_MESSAGE);
    }

    @Test
    public void shouldContainsProductNotFoundMessage() {
        String expected = "Product not found";
        assertEquals(expected, ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE);
    }

    @Test
    public void shouldContainsProductPriceValidationMessage() {
        String expected = "Product price is Incorrect, price must be more than ZERO";
        assertEquals(expected, ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
    }

    @Test
    public void shouldContainsUniqueNameValidationMessage() {
        String expected = "Product name must be unique";
        assertEquals(expected, ValidationExceptionMessages.UNIQUE_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void shouldContainsProductValidationMessage() {
        String expected = "Product must be not null";
        assertEquals(expected, ValidationExceptionMessages.PRODUCT_VALIDATION_MESSAGE);
    }
}