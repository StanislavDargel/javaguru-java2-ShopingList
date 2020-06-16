package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(3)
public class RemoveProduct implements ActionMenu {
    private final ValidationService service;

    public RemoveProduct(ValidationService service) {
        this.service = service;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product delete menu. Enter product id: ");
        Long inputNum = Long.parseLong(scanner.nextLine());
        ProductDTO foundedProduct = service.findById(inputNum);
        String foundedProductInfo = service.printProductInfo(foundedProduct);
        System.out.println(foundedProductInfo);
        System.out.print("Are you sure you want to delete this product (Y/N)?");
        String inputAnswer = scanner.nextLine();
        if (isAgree(inputAnswer)) {
            ProductDTO removableProduct = service.removeProduct(inputNum);
            System.out.println("Product " + removableProduct.getName() + " was successfully deleted");
        }

    }

    @Override
    public String toString() {
        return "Remove product";
    }
}
