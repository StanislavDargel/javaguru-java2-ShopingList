package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.actions.ActionMenu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private final List<ActionMenu> actions;

    public ConsoleUI(List<ActionMenu> actions) {
        this.actions = actions;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Select menu: ");
                for (ActionMenu menu : actions) {
                    System.out.println(actions.indexOf(menu) + ". " + menu);
                }
                while (!scanner.hasNextInt()) {
                    System.out.println("Input doesn't match specifications. Try again and enter number from menu.");
                    System.out.print("Select menu: ");
                    scanner.next();
                }
                int inputNum = scanner.nextInt();
                if (inputNum < 0 || inputNum >= actions.size()) {
                    System.out.print("The selected menu doesn't exist, please try again.\n");
                }
                actions.get(inputNum).action();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}