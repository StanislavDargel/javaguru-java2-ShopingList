package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class ProductUniqueValidationRule implements ProductValidationRule {
    private ProductRepository repository;

    public ProductUniqueValidationRule(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (repository.findProductByName(productDTO.getName()).isPresent()) {
            throw new ProductValidationExceptions(ValidationExceptionMessages.UNIQUE_NAME_VALIDATION_MESSAGE);
        }
    }
}
