package com.javaguru.shoppinglist.console.productparameters;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
@Order(2)
public class ProductPriceInput implements ProductParameters {
    @Override
    public void inputParameter(ProductDTO productDTO) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product price: ");
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Input doesn't match specifications. Try again and enter correct price.");
            System.out.print("Enter product price: ");
            scanner.next();
        }
        BigDecimal inputPrice = scanner.nextBigDecimal();
        productDTO.setPrice(inputPrice);
    }

    @Override
    public String toString() {
        return "Product price";
    }
}
