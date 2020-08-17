package com.javaguru.shoppinglist.console.productparameters;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(0)
public class ProductCategoryInput implements ProductParameters {
    @Override
    public void inputParameter(ProductDTO productDTO) {
        Scanner scanner = new Scanner(System.in);
        ProductCategory.printProductCategory();
        ProductCategory[] categories = ProductCategory.values();
        while (true) {
            System.out.print("Select product category: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Input doesn't match specifications. Try again and enter number from list.");
                System.out.print("Select product category: ");
                scanner.next();
            }
            int inputNum = scanner.nextInt();
            if (inputNum < 0 || inputNum >= categories.length) {
                System.out.println("Selected product category doesn't exist.");
            } else {
                productDTO.setCategory(categories[inputNum]);
                break;
            }
        }
    }

    @Override
    public String parameterName() {
        return "Product category";
    }
}
