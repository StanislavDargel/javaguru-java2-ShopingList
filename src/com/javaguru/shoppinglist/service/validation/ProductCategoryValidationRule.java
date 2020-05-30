package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductCategoryValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        productNotNull(product);
        if (product.getCategory() == null) {
            throw new ProductValidationExceptions("Product category must be not null");
        }
    }
}