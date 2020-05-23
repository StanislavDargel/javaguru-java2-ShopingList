package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductDiscountValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        productNotNull(product);
        if (product.getDiscount() != null) {
            if (product.getPrice().compareTo(new BigDecimal(20.00)) <= 0) {
                product.setDiscount(BigDecimal.ZERO);
            } else if (product.getDiscount().compareTo(BigDecimal.ZERO) < 0 || product.getDiscount().compareTo(new BigDecimal(100.0)) > 0) {
                throw new ProductValidationExceptions("Product discount is Incorrect, (Correct bounds (0 ~ 100.0))");
            } else {
                product.setDiscount(product.getDiscount().setScale(1, RoundingMode.HALF_EVEN));
                product.setActualPrice(product.getPrice().subtract(product.getPrice().movePointLeft(2).multiply(product.getDiscount())).setScale(2, RoundingMode.HALF_EVEN));
            }
        }
    }
}