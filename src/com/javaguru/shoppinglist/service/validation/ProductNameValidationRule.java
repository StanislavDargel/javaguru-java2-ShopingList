package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        productNotNull(product);
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ProductValidationExceptions("Product name must be not empty");
        } else if (product.getName().length() < 3 || product.getName().length() > 32) {
            throw new ProductValidationExceptions("Product name length is incorrect (3 - 32 letters)");
        } else {
            product.setName(product.getName().substring(0, 1).toUpperCase() + product.getName().substring(1).toLowerCase());
        }
    }
}