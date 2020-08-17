package com.javaguru.shoppinglist.console.actions;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(8)
public class CloseProgram implements ActionMenu {
    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure, you want to close the program (Y/N)?");
        String inputAnswer = scanner.nextLine();
        if (isAgree(inputAnswer)) {
            System.out.print("Program closed successfully");
            System.exit(0);
        }
    }

    @Override
    public String actionName() {
        return "Close program";
    }
}