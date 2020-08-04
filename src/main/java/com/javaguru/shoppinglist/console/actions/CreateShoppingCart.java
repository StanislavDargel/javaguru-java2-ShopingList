package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.domain.ShoppingCartEntity;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Scanner;

@Component
@Order(4)
public class CreateShoppingCart implements ActionMenu {
    private final ShoppingCartService shoppingCartService;

    public CreateShoppingCart(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want create a Shopping Cart (Y/N)? ");
        String inputAnswer = scanner.nextLine();
        if (isAgree(inputAnswer)) {
            ShoppingCartEntity entity = new ShoppingCartEntity();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String shoppingCartName = "Shopping Cart " + timestamp;
            entity.setName(shoppingCartName);
            ShoppingCartEntity output = shoppingCartService.save(entity);
            System.out.print("Shopping Cart created successfully\n" + output);
        }
    }

    @Override
    public String actionName() {
        return "Create Shopping Cart";
    }
}
