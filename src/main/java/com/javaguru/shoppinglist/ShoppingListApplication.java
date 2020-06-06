package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.console.actions.*;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.repository.ProductRepositoryImpl;
import com.javaguru.shoppinglist.service.ValidationService;
import com.javaguru.shoppinglist.service.validation.*;

import java.util.ArrayList;
import java.util.List;

class ShoppingListApplication {

    public static void main(String[] args) {
        ProductRepository repository = new ProductRepositoryImpl();
        BeanMapper beanMapper = new BeanMapper();
        List<ProductValidationRule> validationRules = new ArrayList<>();
        validationRules.add(new ProductNameValidationRule());
        validationRules.add(new ProductPriceValidationRule());
        validationRules.add(new ProductCategoryValidationRule());
        validationRules.add(new ProductDescriptionValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
        validationRules.add(new ProductUniqueValidationRule(repository));
        ProductValidationService validationService = new ProductValidationService(validationRules);
        ValidationService service = new ValidationService(repository, validationService, beanMapper);
        List<ActionMenu> actions = new ArrayList<>();
        actions.add(new CreateProduct(service));
        actions.add(new FindProductById(service));
        actions.add(new EditParameters(service));
        actions.add(new RemoveProduct(service));
        ConsoleUI consoleUI = new ConsoleUI(actions);
        consoleUI.execute();
    }
}