package org.example;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(args[0], args[1]);
        ui.display();
    }
}