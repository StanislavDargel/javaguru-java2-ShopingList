package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductValidationService {
    private final Map<Integer, ProductValidationRule> validationRules = new HashMap<>();

    public ProductValidationService() {
        validationRules.put(0, new ProductNameValidationRule());
        validationRules.put(1, new ProductPriceValidationRule());
        validationRules.put(2, new ProductCategoryValidationRule());
        validationRules.put(3, new ProductDescriptionValidationRule());
        validationRules.put(4, new ProductDiscountValidationRule());
    }

    public void validateProduct(ProductDTO productDTO) {
        validationRules.forEach((key, value) -> value.validate(productDTO));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductValidationService)) return false;
        ProductValidationService that = (ProductValidationService) o;
        return Objects.equals(validationRules, that.validationRules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validationRules);
    }

    @Override
    public String toString() {
        return "ProductValidationService{" +
                "validationRules=" + validationRules +
                '}';
    }
}