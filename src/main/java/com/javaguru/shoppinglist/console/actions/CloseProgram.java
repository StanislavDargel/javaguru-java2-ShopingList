package com.javaguru.shoppinglist.console.actions;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(4)
public class CloseProgram implements ActionMenu {
    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure, you want to close a program (Y/N)?");
        String inputAnswer = scanner.nextLine();
        if (isAgree(inputAnswer)) {
            System.out.print("Program was successfully closed");
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return "Close program";
    }
}
