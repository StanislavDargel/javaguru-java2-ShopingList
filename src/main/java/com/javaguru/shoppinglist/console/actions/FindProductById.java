package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;

import java.util.Scanner;

public class FindProductById implements ActionMenu {
    private final ValidationService service;

    public FindProductById(ValidationService service) {
        this.service = service;
    }

    @Override
    public void action(Integer actionNum) {
        if (actionNum.equals(2)) {
            System.out.print("\nProduct search menu. Enter product id: ");
            Scanner scanner = new Scanner(System.in);
            Long inputNum = scanner.nextLong();
            ProductDTO foundedProduct = service.findById(inputNum);
            service.printProductInfo(foundedProduct);
        }
    }
}
