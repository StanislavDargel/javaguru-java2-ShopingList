package com.javaguru.shoppinglist.console.actions;

public interface ActionMenu {
    void action(Integer actionNum);

    default boolean isAgreement(String answer) {
        return (answer.equalsIgnoreCase("y"));
    }
}
