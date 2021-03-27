package com.src.CLI;

import java.io.FileNotFoundException;
import java.io.IOException;
/*
    CLIStrategy is an interface that defines necessary methods for Command Line Interface classes (since every role
    in the system is presented with different front-end menu, we need multiple CLI classes)
    Methods include presenting front-end menu for users and execute commands entered.

    Pattern: Strategy (Interface)
 */
public interface CLIStrategy {
    void menu() throws IOException;

    String execute(String command) throws FileNotFoundException;

    String[] tokenize(String command);
}
