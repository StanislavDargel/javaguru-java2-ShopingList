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
    public void action(Integer actionNum) {
        if (actionNum.equals(3)) {
            System.out.println("\nEditor Menu. Enter product id: ");
            Scanner scanner = new Scanner(System.in);
            Long inputID = Long.parseLong(scanner.nextLine());
            ProductDTO foundedProductDTO = service.findById(inputID);
            service.printProductInfo(foundedProductDTO);

            System.out.print("\nEdit product name (Y/N)?");
            String nameEditAnswer = scanner.nextLine();
            if (isAgreement(nameEditAnswer)) {
                System.out.print("\nEnter new product name: ");
                String inputName = scanner.nextLine();
                foundedProductDTO.setName(inputName);
            }

            System.out.print("\nEdit product price (Y/N)?");
            String priceEditAnswer = scanner.nextLine();
            if (isAgreement(priceEditAnswer)) {
                System.out.print("\nEnter new product price: ");
                BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
                foundedProductDTO.setPrice(inputPrice);
            }

            System.out.print("\nEdit description (Y/N)?");
            String descriptionEditAnswer = scanner.nextLine().toUpperCase();
            if (isAgreement(descriptionEditAnswer)) {
                System.out.print("\nRemove description (Y/N)?");
                String removeEditDescription = scanner.nextLine().toUpperCase();
                if (isAgreement(removeEditDescription)) {
                    foundedProductDTO.setDescription(new ProductDTO().getDescription());
                } else {
                    System.out.print("\nEnter product description: ");
                    String inputDescription = scanner.nextLine();
                    foundedProductDTO.setDescription(inputDescription);
                }
            }

            if (foundedProductDTO.getPrice().compareTo(new BigDecimal(20.00)) >= 0) {
                System.out.print("\nDo you want set discount for product (Y/N)?");
                String discountEditAnswer = scanner.nextLine();
                if (isAgreement(discountEditAnswer)) {
                    System.out.print("\nEnter product discount: ");
                    BigDecimal discount = new BigDecimal(scanner.nextLine());
                    foundedProductDTO.setDiscount(discount);
                }
            }

            ProductDTO changedProduct = service.changeParameters(inputID, foundedProductDTO);
            service.printProductInfo(changedProduct);
        }
    }
}
