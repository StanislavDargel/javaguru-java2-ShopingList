package com.javaguru.shoppinglist.console.actions;

import java.util.List;

public class ConsoleUIMenu {
    private final List<ActionMenu> actions;

    public ConsoleUIMenu(List<ActionMenu> actions) {
        this.actions = actions;
    }

    public void executeAction(Integer actionNum) {
        actions.forEach(action -> action.action(actionNum));
    }
}
