package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.productutils.ProductDataNormalizer;
import com.javaguru.shoppinglist.productutils.ProductInfo;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(3)
public class RemoveProduct implements ActionMenu {
    private final ProductService service;
    private final ProductInfo productInfo;
    private final ProductDataNormalizer normalizer;

    public RemoveProduct(ProductService service,
                         ProductInfo productInfo,
                         ProductDataNormalizer normalizer) {
        this.service = service;
        this.productInfo = productInfo;
        this.normalizer = normalizer;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product delete menu. Enter product id: ");
        Long inputNum = Long.parseLong(scanner.nextLine());
        ProductDTO foundedProduct = service.findById(inputNum);
        normalizer.normalize(foundedProduct);
        String foundedProductInfo = productInfo.print(foundedProduct);
        System.out.println(foundedProductInfo);
        System.out.print("Are you sure you want to delete this product (Y/N)?");
        String inputAnswer = scanner.nextLine();
        if (isAgree(inputAnswer)) {
            service.removeProduct(inputNum);
            System.out.println("Product deleted successfully");
        }
    }

    @Override
    public String actionName() {
        return "Remove product";
    }
}
