package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        productNotNull(product);
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductValidationExceptions("Product price is Incorrect, price must be more than ZERO");
        }
    }
}