package com.javaguru.shoppinglist.console.productparameters;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(1)
public class ProductNameInput implements ProductParameters {
    @Override
    public void inputParameter(ProductDTO productDTO) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter product name: ");
        String inputName = scanner.nextLine();
        for (int i = 0; i < inputName.length(); i++) {
            while (!Character.isLetter(inputName.charAt(i))) {
                i = 0;
                System.out.println("Text must contain only letters.");
                System.out.print("Please enter product name: ");
                inputName = scanner.nextLine();
            }
        }
        productDTO.setName(inputName);
    }

    @Override
    public String parameterName() {
        return "Product name";
    }
}
