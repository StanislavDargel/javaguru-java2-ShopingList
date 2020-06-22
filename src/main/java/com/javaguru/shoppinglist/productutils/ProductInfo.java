package com.javaguru.shoppinglist.productutils;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.lang.String.format;

@Component
public class ProductInfo {
    public String print(ProductDTO productDTO) {
        String info = format("\nName: %s\nID: %d\nPrice: %.2f\nCategory: %s%n",
                productDTO.getName(), productDTO.getId(), productDTO.getPrice(), productDTO.getCategory());
        if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()) {
            info += format("Description: %s%n", productDTO.getDescription());
        }
        if (productDTO.getDiscount() != null && productDTO.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            info += format("Discount: %.1f %% \nActual Price: %.2f%n",
                    productDTO.getDiscount(), productDTO.getActualPrice());
        }
        return info;
    }
}
