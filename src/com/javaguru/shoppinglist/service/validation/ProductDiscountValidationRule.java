package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        productNotNull(product);
        if (product.getDiscount() != null) {
            if (product.getDiscount().compareTo(BigDecimal.ZERO) < 0 || product.getDiscount().compareTo(new BigDecimal(100.0)) > 0) {
                throw new ProductValidationExceptions("Product discount is Incorrect, (Correct bounds (0 ~ 100.0))");
            }
        }
    }
}
