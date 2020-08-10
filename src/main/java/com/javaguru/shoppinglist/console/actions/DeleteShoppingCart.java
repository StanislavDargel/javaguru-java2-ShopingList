package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.service.ShoppingCartService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(7)
public class DeleteShoppingCart implements ActionMenu {
    private final ShoppingCartService shoppingCartService;

    public DeleteShoppingCart(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart id: ");
        Long inputId = Long.parseLong(scanner.nextLine());
        System.out.println("Are you sure you want to delete shopping cart (Y/N)?");
        String inputAnswer = scanner.nextLine();
        if (isAgree(inputAnswer)) {
            shoppingCartService.removeShoppingCart(inputId);
            System.out.println("Shopping cart with id: " + inputId + " deleted successfully");
        }
    }

    @Override
    public String actionName() {
        return "Delete Shopping Cart";
    }
}
