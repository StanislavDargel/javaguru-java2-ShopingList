package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.repository.ProductRepositoryImpl;
import com.javaguru.shoppinglist.service.ValidationService;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

class ShoppingListApplication {

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI(new ValidationService(new ProductRepositoryImpl(), new ProductValidationService(), new BeanMapper()));
        consoleUI.execute();
    }
}