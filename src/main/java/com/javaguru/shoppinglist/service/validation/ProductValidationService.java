package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

import java.util.List;

public class ProductValidationService {
    private final List<ProductValidationRule> validationRules;

    public ProductValidationService(List<ProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validateProduct(ProductDTO productDTO) {
        validationRules.forEach(rule -> rule.validate(productDTO));
    }
}