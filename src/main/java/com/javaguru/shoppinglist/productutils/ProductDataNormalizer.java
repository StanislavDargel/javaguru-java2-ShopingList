package com.javaguru.shoppinglist.productutils;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ProductDataNormalizer {
    public ProductDTO normalize(ProductDTO productDTO) {
        productDTO.setName(productDTO.getName().substring(0, 1).toUpperCase() + productDTO.getName().substring(1).toLowerCase());
        productDTO.setPrice(productDTO.getPrice().setScale(2, RoundingMode.HALF_EVEN));
        if (productDTO.getPrice().compareTo(new BigDecimal("20.00")) >= 0 && productDTO.getDiscount() != null) {
            productDTO.setDiscount(productDTO.getDiscount().setScale(1, RoundingMode.FLOOR));
            productDTO.setActualPrice(productDTO.getPrice().subtract(productDTO.getPrice().movePointLeft(2).multiply(productDTO.getDiscount())).setScale(2, RoundingMode.HALF_EVEN));
        } else {
            productDTO.setDiscount(BigDecimal.ZERO);
        }
        return productDTO;
    }
}
