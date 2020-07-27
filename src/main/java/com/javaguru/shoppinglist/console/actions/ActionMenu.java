package com.javaguru.shoppinglist.console.actions;

public interface ActionMenu {
    void action();

    String actionName();

    default boolean isAgree(String answer) {
        return (answer.equalsIgnoreCase("y"));
    }
}
