package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductDescriptionValidationRule implements ProductValidationRule {
    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getDescription() != null) {
            if (productDTO.getDescription().isEmpty()) {
                throw new ProductValidationExceptions(ValidationExceptionMessages.DESCRIPTION_VALIDATION_MESSAGE);
            }
        }
    }
}