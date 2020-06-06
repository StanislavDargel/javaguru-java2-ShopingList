package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;

import java.util.Scanner;

public class RemoveProduct implements ActionMenu {
    private final ValidationService service;

    public RemoveProduct(ValidationService service) {
        this.service = service;
    }

    @Override
    public void action(Integer actionNum) {
        if (actionNum.equals(4)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nProduct delete menu. Enter product id: ");
            Long inputID = Long.parseLong(scanner.nextLine());
            ProductDTO removableProduct = service.removeProduct(inputID);
            System.out.println(removableProduct + "\nWas successfully deleted");
        }
    }
}
