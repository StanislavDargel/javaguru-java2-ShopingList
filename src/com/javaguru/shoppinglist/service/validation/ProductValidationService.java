package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductValidationService {
    private static volatile ProductValidationService productService;
    private final Set<ProductValidationRule> validationRules = new HashSet<>();

    private ProductValidationService() {
        validationRules.add(new ProductNameValidationRule());
        validationRules.add(new ProductPriceValidationRule());
        validationRules.add(new ProductCategoryValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
        validationRules.add(new ProductDescriptionValidationRule());
        validationRules.add(new ProductActualPriceValidationRule());
    }

    public static ProductValidationService getProductService() {
        if (productService == null) {
            synchronized (ProductValidationService.class) {
                if (productService == null) {
                    productService = new ProductValidationService();
                }
            }
        }
        return productService;
    }

    public void validateProduct(Product product) {
        validationRules.forEach(rule -> rule.validate(product));
    }
}