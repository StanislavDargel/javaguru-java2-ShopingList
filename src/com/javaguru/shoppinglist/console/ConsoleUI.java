package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.repository.ProductRepositoryImp;
import com.javaguru.shoppinglist.service.ValidationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {
    private static volatile ConsoleUI console;

    private ConsoleUI() {

    }

    public static ConsoleUI getConsole() {
        if (console == null) {
            synchronized (ProductRepositoryImp.class) {
                if (console == null) {
                    console = new ConsoleUI();
                }
            }
        }
        return console;
    }

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Edit product");
                System.out.println("4. Remove product");
                System.out.println("5. Exit");
                Integer inputNum = Integer.parseInt(scanner.nextLine());
                switch (inputNum) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        System.out.println("Product search menu. Enter product id: ");
                        Long idOne = scanner.nextLong();
                        printInfo(ValidationService.getService().findById(idOne));
                        break;
                    case 3:
                        editProductParameters();
                        break;
                    case 4:
                        removeProduct();
                        break;

                    case 5:
                        return;
                    default:
                        System.out.println("Menu line with number " + inputNum + " doesn't exist");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Product creation Menu.");
        Product product = new Product();
        ProductCategory.printProductCategory();
        choiceCategories(product);
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        product.setName(name);
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        product.setPrice(price);
        if (price.compareTo(new BigDecimal(20.00)) < 0) {
            System.out.println("To set Discount on product, Product price must be more than 20.00 Euro");
        } else {
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
        printInfo(ValidationService.getService().saveProduct(product));
    }

    private static void choiceCategories(Product product) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select product category: ");
        Integer caseNum = Integer.parseInt(scanner.nextLine());
        switch (caseNum) {
            case 0:
                product.setCategory(ProductCategory.FRUIT);
                break;
            case 1:
                product.setCategory(ProductCategory.DRINK);
                break;
            case 2:
                product.setCategory(ProductCategory.MEAT);
                break;
            case 3:
                product.setCategory(ProductCategory.VEGETABLE);
                break;
            case 4:
                product.setCategory(ProductCategory.CANDY);
                break;
            case 5:
                product.setCategory(ProductCategory.DAIRY_PRODUCTS);
                break;
            case 6:
                product.setCategory(ProductCategory.BAKERY);
                break;
            case 7:
                product.setCategory(ProductCategory.SEA_FOODS);
                break;
            case 8:
                product.setCategory(ProductCategory.CANNED_FOOD);
                break;
            case 9:
                product.setCategory(ProductCategory.GROAT);
                break;
            default:
                System.out.println("Category with number " + caseNum + " doesn't exist");
                break;
        }
    }

    private static void printInfo(Product product) {
        if (product.getDescription() != null && !product.getDescription().isEmpty()
                && product.getDiscount() != null && product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("\nName: " + product.getName() +
                    "\nID: " + product.getId() +
                    "\nRegular Price: " + product.getPrice() +
                    "\nCategory: " + product.getCategory() +
                    "\nDiscount: " + product.getDiscount() + "%" +
                    "\nActual Price: " + product.getActualPrice() +
                    "\nDescription: " + product.getDescription());
        } else if (product.getDiscount() != null && product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("\nName: " + product.getName() +
                    "\nID: " + product.getId() +
                    "\nRegular Price: " + product.getPrice() +
                    "\nCategory: " + product.getCategory() +
                    "\nDiscount: " + product.getDiscount() + "%" +
                    "\nActual Price: " + product.getActualPrice());
        } else if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            System.out.println("\nName: " + product.getName() +
                    "\nID: " + product.getId() +
                    "\nActual Price: " + product.getPrice() +
                    "\nCategory: " + product.getCategory() +
                    "\nDescription: " + product.getDescription());
        } else {
            System.out.println("\nName: " + product.getName() +
                    "\nID: " + product.getId() +
                    "\nActual Price: " + product.getPrice() +
                    "\nCategory: " + product.getCategory());
        }
    }

    private static void editProductParameters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editor Menu. Enter product id: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product foundedProduct = ValidationService.getService().findById(id);
        printInfo(foundedProduct);
        if (foundedProduct.getPrice().compareTo(new BigDecimal(20.00)) < 0) {
            System.out.println("To set Discount on product, Product price must be more than 20.00 Euro");
            System.out.println("Select product field to edit: " +
                    "\n1. Name" +
                    "\n2. Product price" +
                    "\n3. Product description");
            Integer caseNum = Integer.parseInt(scanner.nextLine());
            switch (caseNum) {
                case 1:
                    System.out.println("Enter product name: ");
                    String inputName = scanner.nextLine();
                    foundedProduct.setName(inputName);
                    break;
                case 2:
                    System.out.println("Enter product price: ");
                    BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
                    foundedProduct.setPrice(inputPrice);
                    break;
                case 3:
                    System.out.println("Enter product description: ");
                    String inputDescription = scanner.nextLine();
                    foundedProduct.setDescription(inputDescription);
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Select product field to edit: " +
                    "\n1. Name" +
                    "\n2. Product price" +
                    "\n3. Product description" +
                    "\n4. Product discount");
            Integer caseNum = Integer.parseInt(scanner.nextLine());
            switch (caseNum) {
                case 1:
                    System.out.println("Enter product name: ");
                    String inputName = scanner.nextLine();
                    foundedProduct.setName(inputName);
                    break;
                case 2:
                    System.out.println("Enter product price: ");
                    BigDecimal inputPrice = new BigDecimal(scanner.nextLine());
                    foundedProduct.setPrice(inputPrice);
                    break;
                case 3:
                    System.out.println("Enter product description: ");
                    String inputDescription = scanner.nextLine();
                    foundedProduct.setDescription(inputDescription);
                    break;
                case 4:
                    System.out.println("Enter product discount: ");
                    BigDecimal inputDiscount = new BigDecimal(scanner.nextLine());
                    foundedProduct.setDiscount(inputDiscount);
                    break;
                default:
                    break;
            }
        }
        printInfo(ValidationService.getService().changeParameters(id, foundedProduct));
    }

    private static void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Product delete menu. Enter product id: ");
        Long id = Long.parseLong(scanner.nextLine());
        if (ValidationService.getService().removeProduct(id) == null) {
            System.out.println("Product with ID " + id + ", was successfully deleted");
        }
    }
}