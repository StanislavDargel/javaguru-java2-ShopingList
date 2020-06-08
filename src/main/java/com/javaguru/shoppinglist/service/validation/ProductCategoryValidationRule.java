package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

public class ProductCategoryValidationRule implements ProductValidationRule {

    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getCategory() == null) {
            throw new ProductValidationExceptions(ValidationExceptionMessages.CATEGORY_VALIDATION_MESSAGE);
        }
    }
}