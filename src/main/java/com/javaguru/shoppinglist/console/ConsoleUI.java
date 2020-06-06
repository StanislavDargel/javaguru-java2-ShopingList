package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.actions.ActionMenu;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final List<ActionMenu> actions;

    public ConsoleUI(List<ActionMenu> actions) {
        this.actions = actions;
    }

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Select menu: ");
                for (ActionMenu menu : actions) {
                    System.out.println(actions.indexOf(menu) + ". " + menu.getClass().getSimpleName());
                }
                System.out.println(actions.size() + ". Exit");
                Integer inputNum = Integer.parseInt(scanner.nextLine());
                if (inputNum < 0 || inputNum > actions.size()) {
                    System.out.println("Menu with number " + inputNum + " doesn't exist");
                } else if (inputNum.equals(actions.size())) {
                    scanner.close();
                    System.exit(0);
                } else {
                    actions.get(inputNum).action();
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}