package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule {
    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getPrice() == null || productDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductValidationExceptions(ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
        }
    }
}