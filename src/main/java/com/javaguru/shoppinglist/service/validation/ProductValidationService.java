package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

import java.util.Set;

public class ProductValidationService {
    private final Set<ProductValidationRule> validationRules;

    public ProductValidationService(Set<ProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validateProduct(ProductDTO productDTO) {
        validationRules.forEach(rule -> rule.validate(productDTO));
    }
}