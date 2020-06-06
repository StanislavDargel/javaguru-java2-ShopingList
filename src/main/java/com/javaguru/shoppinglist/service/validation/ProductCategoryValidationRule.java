package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

public class ProductCategoryValidationRule implements ProductValidationRule {

    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getCategory() == null) {
            throw new ProductValidationExceptions("Product category must be not null");
        }
    }
}