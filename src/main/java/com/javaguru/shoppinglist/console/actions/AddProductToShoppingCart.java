package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.domain.ShoppingCartEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(6)
public class AddProductToShoppingCart implements ActionMenu {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final BeanMapper beanMapper;

    public AddProductToShoppingCart(ShoppingCartService shoppingCartService, ProductService productService, BeanMapper beanMapper) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.beanMapper = beanMapper;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter shopping cart id: ");
        Long shoppingCartId = scanner.nextLong();
        System.out.println("Please enter product id: ");
        Long productId = scanner.nextLong();
        ProductDTO foundedProduct = productService.findById(productId);
        ProductEntity entity = beanMapper.toProductEntity(foundedProduct);
        shoppingCartService.addProductToShoppingCart(shoppingCartId, entity);
        System.out.println(shoppingCartService.findSoppingCartById(shoppingCartId));
    }

    @Override
    public String actionName() {
        return "Add product to shopping cart";
    }
}
