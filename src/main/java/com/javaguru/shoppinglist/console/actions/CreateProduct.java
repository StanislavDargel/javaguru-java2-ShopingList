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
    public void action(Integer actionNum) {
        if (actionNum.equals(1)) {
            System.out.println("\nProduct creation menu.");
            Scanner scanner = new Scanner(System.in);
            ProductDTO productDTO = new ProductDTO();

            ProductCategory.printProductCategory();
            System.out.print("\nSelect product category: ");
            Integer caseNum = Integer.parseInt(scanner.nextLine());
            ProductCategory[] categories = ProductCategory.values();
            if (caseNum < 0 || caseNum >= categories.length) {
                throw new IllegalArgumentException("Category with number " + caseNum + " doesn't exist");
            }
            productDTO.setCategory(categories[caseNum]);

            System.out.print("\nEnter product name: ");
            String inputName = scanner.nextLine();
            productDTO.setName(inputName);

            System.out.print("\nEnter product price: ");
            BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
            productDTO.setPrice(inputPrice);

            if (inputPrice.compareTo(new BigDecimal(20.00)) >= 0) {
                System.out.print("\nDo you want set discount for product (Y/N)?");
                String discountAnswer = scanner.nextLine();
                if (isAgreement(discountAnswer)) {
                    System.out.print("\nEnter product discount: ");
                    BigDecimal discount = new BigDecimal(scanner.nextLine());
                    productDTO.setDiscount(discount);
                }
            }

            System.out.print("\nDo you want set description for product (Y/N)?");
            String descriptionAnswer = scanner.nextLine();
            if (isAgreement(descriptionAnswer)) {
                System.out.print("\nEnter product description: ");
                String description = scanner.nextLine();
                productDTO.setDescription(description);
            }

            ProductDTO savedProduct = service.saveProduct(productDTO);
            service.printProductInfo(savedProduct);
        }
    }
}
