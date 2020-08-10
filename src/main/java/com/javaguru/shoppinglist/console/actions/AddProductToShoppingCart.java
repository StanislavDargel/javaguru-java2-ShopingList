package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.service.ShoppingCartService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(6)
public class AddProductToShoppingCart implements ActionMenu {
    private final ShoppingCartService shoppingCartService;

    public AddProductToShoppingCart(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter shopping cart id: ");
        Long shoppingCartId = scanner.nextLong();
        System.out.println("Please enter product id: ");
        Long productId = scanner.nextLong();
        shoppingCartService.addProductToShoppingCart(shoppingCartId, productId);
        System.out.println(shoppingCartService.findSoppingCartById(shoppingCartId));
    }

    @Override
    public String actionName() {
        return "Add product to shopping cart";
    }
}
