package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

public interface ProductValidationRule {
    void validate(ProductDTO productDTO);

    default void productNotNull(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new ProductValidationExceptions("Product must be not null");
        }
    }
}