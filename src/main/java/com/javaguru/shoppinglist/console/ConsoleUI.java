package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {
    private final ValidationService service;

    public ConsoleUI(ValidationService service) {
        this.service = service;
    }

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("\n1. Create product" +
                        "\n2. Find product by id" +
                        "\n3. Edit product" +
                        "\n4. Remove product" +
                        "\n5. Exit" +
                        "\nSelect menu: ");
                Integer inputNum = Integer.parseInt(scanner.nextLine());
                switch (inputNum) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        System.out.print("\nProduct search menu. Enter product id: ");
                        Long idOne = scanner.nextLong();
                        this.service.printProductInfo(this.service.findById(idOne));
                        break;
                    case 3:
                        editProductParameters();
                        break;
                    case 4:
                        removeProduct();
                        break;

                    case 5:
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Menu line with number " + inputNum + " doesn't exist");
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nError! Please try again.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProduct creation Menu.");
        ProductDTO productDTO = new ProductDTO();
        ProductCategory.printProductCategory();
        choiceCategories(productDTO);
        System.out.print("\nEnter product name: ");
        String inputName = scanner.nextLine();
        productDTO.setName(inputName);
        System.out.print("\nEnter product price: ");
        BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
        productDTO.setPrice(inputPrice);
        System.out.println("\nTo set Discount for product, Product price must be more than 20.00 Euro");
        System.out.print("\nDo you want set discount for product (Y/N)?");
        String discountAnswer = scanner.nextLine().toUpperCase();
        switch (discountAnswer) {
            case "Y":
                System.out.print("\nEnter product discount: ");
                BigDecimal discount = new BigDecimal(scanner.nextLine());
                productDTO.setDiscount(discount);
                break;
            case "N":
                break;
        }
        System.out.print("\nDo you want set description for product (Y/N)?");
        String descriptionAnswer = scanner.nextLine().toUpperCase();
        switch (descriptionAnswer) {
            case "Y":
                System.out.print("\nEnter product description: ");
                String description = scanner.nextLine();
                productDTO.setDescription(description);
                break;
            case "N":
                break;
        }
        this.service.printProductInfo(this.service.saveProduct(productDTO));
    }

    private void choiceCategories(ProductDTO productDTO) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSelect product category: ");
        Integer caseNum = Integer.parseInt(scanner.nextLine());
        switch (caseNum) {
            case 0:
                productDTO.setCategory(ProductCategory.FRUIT);
                break;
            case 1:
                productDTO.setCategory(ProductCategory.DRINK);
                break;
            case 2:
                productDTO.setCategory(ProductCategory.MEAT);
                break;
            case 3:
                productDTO.setCategory(ProductCategory.VEGETABLE);
                break;
            case 4:
                productDTO.setCategory(ProductCategory.CANDY);
                break;
            case 5:
                productDTO.setCategory(ProductCategory.DAIRY_PRODUCTS);
                break;
            case 6:
                productDTO.setCategory(ProductCategory.BAKERY);
                break;
            case 7:
                productDTO.setCategory(ProductCategory.SEA_FOODS);
                break;
            case 8:
                productDTO.setCategory(ProductCategory.CANNED_FOOD);
                break;
            case 9:
                productDTO.setCategory(ProductCategory.GROAT);
                break;
            default:
                System.out.println("Category with number " + caseNum + " doesn't exist");
                break;
        }
    }

    private void editProductParameters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEditor Menu. Enter product id: ");
        Long inputID = Long.parseLong(scanner.nextLine());
        ProductDTO foundedProductDTO = this.service.findById(inputID);
        this.service.printProductInfo(foundedProductDTO);
        System.out.print("\nEdit product name (Y/N)?");
        System.out.print("\nEnter new product name: ");
        String nameEditAnswer = scanner.nextLine().toUpperCase();
        switch (nameEditAnswer) {
            case "Y":
                String inputName = scanner.nextLine();
                foundedProductDTO.setName(inputName);
                break;
            case "N":
                break;
        }
        System.out.print("\nEdit product price (Y/N)?");
        System.out.print("\nEnter new product price: ");
        String priceEditAnswer = scanner.nextLine().toUpperCase();
        switch (priceEditAnswer) {
            case "Y":
                BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
                foundedProductDTO.setPrice(inputPrice);
                break;
            case "N":
                break;
        }
        System.out.print("\nEdit Description (Y/N)?");
        String descriptionAnswer = scanner.nextLine().toUpperCase();
        switch (descriptionAnswer) {
            case "Y":
                System.out.print("\nRemove description (Y/N)?");
                String removeEditDescription = scanner.nextLine().toUpperCase();
                switch (removeEditDescription) {
                    case "Y":
                        foundedProductDTO.setDescription(new ProductDTO().getDescription());
                        break;
                    case "N":
                        System.out.print("\nEnter product description: ");
                        String inputDescription = scanner.nextLine();
                        foundedProductDTO.setDescription(inputDescription);
                        break;
                }
                break;
            case "N":
                break;
        }
        System.out.println("\nTo set Discount for product, Product price must be more than 20.00 Euro");
        System.out.print("\nDo you want set discount for product (Y/N)?");
        String discountAnswer = scanner.nextLine().toUpperCase();
        switch (discountAnswer) {
            case "Y":
                System.out.print("\nEnter product discount: ");
                BigDecimal discount = new BigDecimal(scanner.nextLine());
                foundedProductDTO.setDiscount(discount);
                break;
            case "N":
                break;

        }
        this.service.printProductInfo(this.service.changeParameters(inputID, foundedProductDTO));
    }

    private void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProduct delete menu. Enter product id: ");
        Long inputID = Long.parseLong(scanner.nextLine());
        System.out.println(this.service.removeProduct(inputID) + "\nWas successfully deleted");
    }
}