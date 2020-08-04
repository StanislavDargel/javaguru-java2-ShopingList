package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.productutils.ProductDataNormalizer;
import com.javaguru.shoppinglist.productutils.ProductInfo;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(2)
public class FindProductById implements ActionMenu {
    private final ProductService service;
    private final ProductInfo productInfo;
    private final ProductDataNormalizer normalizer;

    public FindProductById(ProductService service,
                           ProductInfo productInfo,
                           ProductDataNormalizer normalizer) {
        this.service = service;
        this.productInfo = productInfo;
        this.normalizer = normalizer;
    }

    @Override
    public void action() {
        System.out.println("Product search menu. Enter product id: ");
        Scanner scanner = new Scanner(System.in);
        Long inputNum = scanner.nextLong();
        ProductDTO foundedProduct = service.findById(inputNum);
        normalizer.normalize(foundedProduct);
        String foundedProductInfo = productInfo.print(foundedProduct);
        System.out.println(foundedProductInfo);
    }

    @Override
    public String actionName() {
        return "Find product by Id";
    }
}
