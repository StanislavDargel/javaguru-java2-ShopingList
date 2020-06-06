package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreateProduct implements ActionMenu {
    private final ValidationService service;

    public CreateProduct(ValidationService service) {
        this.service = service;
    }

    @Override
    public void action() {
        System.out.println("Product creation menu.");
        Scanner scanner = new Scanner(System.in);
        ProductDTO productDTO = new ProductDTO();

        ProductCategory.printProductCategory();
        System.out.println("Select product category: ");
        int caseNum = Integer.parseInt(scanner.nextLine());
        ProductCategory[] categories = ProductCategory.values();
        if (caseNum < 0 || caseNum >= categories.length) {
            throw new IllegalArgumentException("Category with number " + caseNum + " doesn't exist");
        }
        productDTO.setCategory(categories[caseNum]);

        System.out.println("Enter product name: ");
        String inputName = scanner.nextLine();
        productDTO.setName(inputName);

        System.out.println("Enter product price: ");
        BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
        productDTO.setPrice(inputPrice);

        if (inputPrice.compareTo(new BigDecimal("20.00")) >= 0) {
            System.out.println("Do you want set discount for product (Y/N)?");
            String discountAnswer = scanner.nextLine();
            if (isAgree(discountAnswer)) {
                System.out.println("Enter product discount: ");
                BigDecimal discount = new BigDecimal(scanner.nextLine());
                productDTO.setDiscount(discount);
            }
        }

        System.out.println("Do you want set description for product (Y/N)?");
        String descriptionAnswer = scanner.nextLine();
        if (isAgree(descriptionAnswer)) {
            System.out.println("Enter product description: ");
            String description = scanner.nextLine();
            productDTO.setDescription(description);
        }

        ProductDTO savedProduct = service.saveProduct(productDTO);
        service.printProductInfo(savedProduct);
    }
}
