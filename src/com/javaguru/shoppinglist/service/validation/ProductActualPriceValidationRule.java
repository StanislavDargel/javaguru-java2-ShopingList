package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductActualPriceValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        productNotNull(product);
        if (product.getActualPrice() != null) {
            if (product.getActualPrice().compareTo(BigDecimal.ZERO) < 0) {
                throw new ProductValidationExceptions("Actual price must more than or equals to zero.");
            }
        }
    }
}
