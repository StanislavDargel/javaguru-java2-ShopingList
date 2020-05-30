package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public interface ProductValidationRule {
    void validate(Product product);

    default void productNotNull(Product product) {
        if (product == null) {
            throw new ProductValidationExceptions("Product must be not null");
        }
    }
}