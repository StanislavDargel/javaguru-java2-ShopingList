package com.javaguru.shoppinglist.console.actions;

public interface ActionMenu {
    void action();

    default boolean isAgree(String answer) {
        return (answer.equalsIgnoreCase("y"));
    }
}
