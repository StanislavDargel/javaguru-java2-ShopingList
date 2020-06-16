package com.javaguru.shoppinglist.console.productparameters;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
@Order(3)
public class ProductDiscountInput implements ProductParameters {
    @Override
    public void inputParameter(ProductDTO productDTO) {
        Scanner scanner = new Scanner(System.in);
        if (productDTO.getPrice().compareTo(new BigDecimal("20.00")) >= 0) {
            System.out.print("Do you want to set discount for product (Y/N)? ");
            String inputAnswer = scanner.nextLine();
            if (inputAnswer.equalsIgnoreCase("y")) {
                System.out.println("Enter product discount: ");
                while (!scanner.hasNextBigDecimal()) {
                    System.out.println("Input doesn't match specifications. Try again and enter correct discount.");
                    System.out.print("Enter product discount: ");
                    scanner.next();
                }
                BigDecimal discount = scanner.nextBigDecimal();
                productDTO.setDiscount(discount);
            }
        }
    }

    @Override
    public String toString() {
        return "Product discount";
    }
}
