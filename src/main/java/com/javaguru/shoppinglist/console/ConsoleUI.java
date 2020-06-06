package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.actions.ConsoleUIMenu;
import com.javaguru.shoppinglist.service.ValidationService;

import java.util.Scanner;

public class ConsoleUI {
    private final ValidationService service;
    private final ConsoleUIMenu menu;

    public ConsoleUI(ValidationService service, ConsoleUIMenu menu) {
        this.service = service;
        this.menu = menu;
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
                if (inputNum < 1 || inputNum > 5) {
                    System.out.println("Menu with number " + inputNum + " doesn't exist");
                } else if (inputNum.equals(5)) {
                    scanner.close();
                    System.exit(0);
                } else {
                    menu.executeAction(inputNum);
                }
            } catch (Exception e) {
                System.out.println("\nError! Please try again.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}