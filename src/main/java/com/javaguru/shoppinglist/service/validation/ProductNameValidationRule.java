package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

public class ProductNameValidationRule implements ProductValidationRule {
    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
            throw new ProductValidationExceptions(ValidationExceptionMessages.EMPTY_NAME_VALIDATION_MESSAGE);
        } else if (productDTO.getName().length() < 3 || productDTO.getName().length() > 32) {
            throw new ProductValidationExceptions(ValidationExceptionMessages.INCORRECT_NAME_LENGTH_VALIDATION_MESSAGE);
        }
    }
}