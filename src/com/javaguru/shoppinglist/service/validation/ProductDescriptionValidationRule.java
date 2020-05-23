package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductDescriptionValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        productNotNull(product);
        if (product.getDescription() != null) {
            if (product.getDescription().isEmpty()) {
                throw new ProductValidationExceptions("Product description must be not empty");
            }
        }
    }
}