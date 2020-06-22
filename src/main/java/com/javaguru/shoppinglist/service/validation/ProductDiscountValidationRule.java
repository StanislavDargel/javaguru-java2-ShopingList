package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ProductValidationRule {
    @Override
    public void validate(ProductDTO productDTO) {
        productNotNull(productDTO);
        if (productDTO.getDiscount() != null) {
            if (productDTO.getDiscount().compareTo(BigDecimal.ZERO) < 0 ||
                    productDTO.getDiscount().compareTo(new BigDecimal("100.0")) >= 0) {
                throw new ProductValidationExceptions(ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
            }
        }
    }
}
