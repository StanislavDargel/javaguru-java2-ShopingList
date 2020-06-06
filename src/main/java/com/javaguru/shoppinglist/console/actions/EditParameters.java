package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class EditParameters implements ActionMenu {
    private final ValidationService service;

    public EditParameters(ValidationService service) {
        this.service = service;
    }

    @Override
    public void action() {
        System.out.println("Editors Menu. Enter product id: ");
        Scanner scanner = new Scanner(System.in);
        Long inputID = Long.parseLong(scanner.nextLine());
        ProductDTO foundedProductDTO = service.findById(inputID);
        service.printProductInfo(foundedProductDTO);

        System.out.println("Edit product name (Y/N)?");
        String nameEditAnswer = scanner.nextLine();
        if (isAgree(nameEditAnswer)) {
            System.out.println("Enter new product name: ");
            String inputName = scanner.nextLine();
            foundedProductDTO.setName(inputName);
        }

        System.out.println("Edit product price (Y/N)?");
        String priceEditAnswer = scanner.nextLine();
        if (isAgree(priceEditAnswer)) {
            System.out.println("Enter new product price: ");
            BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
            foundedProductDTO.setPrice(inputPrice);
        }

        if (foundedProductDTO.getDescription() != null) {
            System.out.println("Remove description (Y/N)?");
            String removeEditDescription = scanner.nextLine();
            if (isAgree(removeEditDescription)) {
                foundedProductDTO.setDescription(new ProductDTO().getDescription());
            }
        } else {
            System.out.println("Edit description (Y/N)?");
            String descriptionEditAnswer = scanner.nextLine();
            if (isAgree(descriptionEditAnswer)) {
                System.out.println("Enter product description: ");
                String inputDescription = scanner.nextLine();
                foundedProductDTO.setDescription(inputDescription);
            }
        }

        if (foundedProductDTO.getPrice().compareTo(new BigDecimal("20.00")) >= 0) {
            System.out.println("Do you want set discount for product (Y/N)?");
            String discountEditAnswer = scanner.nextLine();
            if (isAgree(discountEditAnswer)) {
                System.out.println("Enter product discount: ");
                BigDecimal discount = new BigDecimal(scanner.nextLine());
                foundedProductDTO.setDiscount(discount);
            }
        }

        ProductDTO changedProduct = service.changeParameters(inputID, foundedProductDTO);
        service.printProductInfo(changedProduct);
    }
}
