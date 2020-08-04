package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.service.ShoppingCartService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class FindAllShoppingCarts implements ActionMenu {
    private final ShoppingCartService shoppingCartService;

    public FindAllShoppingCarts(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void action() {
        shoppingCartService.findAllCarts()
                .forEach(System.out::println);

    }

    @Override
    public String actionName() {
        return "Find all shopping carts";
    }
}
