package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

public class ProductNameValidationRule implements ProductValidationRule {
    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
            throw new ProductValidationExceptions("Product name must be not empty");
        } else if (productDTO.getName().length() < 3 || productDTO.getName().length() > 32) {
            throw new ProductValidationExceptions("Product name length is incorrect (3 - 32 letters)");
        }
    }
}