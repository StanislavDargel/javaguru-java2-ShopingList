package com.javaguru.shoppinglist.console.productparameters;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(4)
public class ProductDescriptionInput implements ProductParameters {
    @Override
    public void inputParameter(ProductDTO productDTO) {
        Scanner scanner = new Scanner(System.in);
        if (productDTO.getDescription() != null) {
            System.out.println("Remove description (Y/N)?");
            String inputAnswerToRemove = scanner.nextLine();
            if (inputAnswerToRemove.equalsIgnoreCase("y")) {
                productDTO.setDescription(new ProductDTO().getDescription());
            }
        } else {
            System.out.println("Please enter description (Y/N)?");
            String inputAnswer = scanner.nextLine();
            if (inputAnswer.equalsIgnoreCase("y")) {
                System.out.println("Enter product description: ");
                String description = scanner.nextLine();
                for (int i = 0; i < description.length(); i++) {
                    while (Character.isDigit(description.charAt(i))) {
                        i = 0;
                        System.out.println("Text must contain only letters.");
                        System.out.print("Please enter product name: ");
                        description = scanner.nextLine();
                    }
                }
                productDTO.setDescription(description);
            }
        }
    }

    @Override
    public String parameterName() {
        return "Product description";
    }
}
