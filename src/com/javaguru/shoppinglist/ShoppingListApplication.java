package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.Scanner;

class ShoppingListApplication {

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Edit product");
                System.out.println("4. Remove product");
                System.out.println("5. Exit");
                Integer userInput = Integer.parseInt(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        Product product = new Product();
                        ProductCategory.printProductCategory();
                        System.out.println("Select product category: ");
                        Integer categoryNum = Integer.parseInt(scanner.nextLine());
                        if (categoryNum < 0 || categoryNum > 10) {
                            System.out.println("Category with number " + categoryNum + " doesn't exist");
                            break;
                        }
                        switch (categoryNum) {
                            case 1:
                                product.setCategory(ProductCategory.FRUIT);
                                break;
                            case 2:
                                product.setCategory(ProductCategory.DRINK);
                                break;
                            case 3:
                                product.setCategory(ProductCategory.MEAT);
                                break;
                            case 4:
                                product.setCategory(ProductCategory.VEGETABLE);
                                break;
                            case 5:
                                product.setCategory(ProductCategory.CANDY);
                                break;
                            case 6:
                                product.setCategory(ProductCategory.DAIRY_PRODUCTS);
                                break;
                            case 7:
                                product.setCategory(ProductCategory.BAKERY);
                                break;
                            case 8:
                                product.setCategory(ProductCategory.SEA_FOODS);
                                break;
                            case 9:
                                product.setCategory(ProductCategory.CANNED_FOOD);
                                break;
                            case 10:
                                product.setCategory(ProductCategory.GROAT);
                                break;
                        }
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        product.setName(name);
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        product.setPrice(price);
                        System.out.println("Do you want set discount on product (Y/N)?");
                        String discountAnswer = scanner.nextLine().toUpperCase();
                        switch (discountAnswer) {
                            case "Y":
                                System.out.println("Enter product discount: ");
                                BigDecimal discount = new BigDecimal(scanner.nextLine());
                                product.setDiscount(discount);
                                break;
                            case "N":
                                break;
                        }
                        System.out.println("Do you want set description on product (Y/N)?");
                        String descriptionAnswer = scanner.nextLine().toUpperCase();
                        switch (descriptionAnswer) {
                            case "Y":
                                System.out.println("Enter product description: ");
                                String description = scanner.nextLine();
                                product.setDescription(description);
                                break;
                            case "N":
                                break;
                        }
                        System.out.println("Result: " + ValidationService.getService().saveProduct(product));
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        Long idOne = scanner.nextLong();
                        System.out.println(ValidationService.getService().findById(idOne));
                        break;
                    case 3:
                        System.out.println("Enter product id: ");
                        Long idTwo = Long.parseLong(scanner.nextLine());
                        Product foundedProduct = ValidationService.getService().findById(idTwo);
                        System.out.println("Select product field to edit: " +
                                "\n1. Name" +
                                "\n2. Product price" +
                                "\n3. Product discount" +
                                "\n4. Product description");
                        Integer editNum = Integer.parseInt(scanner.nextLine());
                        switch (editNum) {
                            case 1:
                                System.out.println("Enter product name: ");
                                String editName = scanner.nextLine();
                                foundedProduct.setName(editName);
                                break;
                            case 2:
                                System.out.println("Enter product price: ");
                                BigDecimal editPrice = new BigDecimal(scanner.nextLine());
                                foundedProduct.setPrice(editPrice);
                                break;
                            case 3:
                                System.out.println("Enter product discount: ");
                                BigDecimal editDiscount = new BigDecimal(scanner.nextLine());
                                foundedProduct.setDiscount(editDiscount);
                                break;
                            case 4:
                                System.out.println("Enter product description: ");
                                String editDescription = scanner.nextLine();
                                foundedProduct.setDescription(editDescription);
                                break;
                        }
                        ValidationService.getService().changeParameters(idTwo, foundedProduct);
                        break;
                    case 4:
                        System.out.println("Enter product id: ");
                        Long idThree = Long.parseLong(scanner.nextLine());
                        Product removedProduct = ValidationService.getService().removeProduct(idThree);
                        if (removedProduct == null) {
                            System.out.println("Product with " + idThree + " id, was successfully removed");
                        }
                        break;

                    case 5:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                System.out.println(e.getMessage());
            }
        }
    }
}
