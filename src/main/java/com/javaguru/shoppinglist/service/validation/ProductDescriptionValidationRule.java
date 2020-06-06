package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

public class ProductDescriptionValidationRule implements ProductValidationRule {
    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getDescription() != null) {
            if (productDTO.getDescription().isEmpty()) {
                throw new ProductValidationExceptions("Product description must be not empty");
            }
        }
    }
}