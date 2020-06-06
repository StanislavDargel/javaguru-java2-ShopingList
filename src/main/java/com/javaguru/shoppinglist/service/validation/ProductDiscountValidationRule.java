package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {
    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getDiscount() != null) {
            if (productDTO.getDiscount().compareTo(BigDecimal.ZERO) < 0 || productDTO.getDiscount().compareTo(new BigDecimal("100.0")) >= 0) {
                throw new ProductValidationExceptions("Product discount is Incorrect, (Correct bounds (0 ~ 100.0))");
            }
        }
    }
}
