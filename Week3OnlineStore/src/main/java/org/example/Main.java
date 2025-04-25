package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = FileLoader.readFile();
        ShoppingCart userCart = new ShoppingCart();

        UserInterface.displayMenu(productList, userCart);
        //TODO: do I pass in the products and cart into displayMenu()?
        //TODO: and then use those in displayMenu, calling and passing those lists into ProductRepository methods?
    }
}