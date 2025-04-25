package org.example;

import java.util.List;

public class ProductRepository {
    //OPTIONAL:
    //filtering functions
    //return product data

    //display all products from a list (essentially toString)
    public static void displayProducts(List<Product> products) {
        //print sku, name, price, department
        for (Product product : products) {
            System.out.println("SKU:\t\t" + product.getSku());
            System.out.println("Name:\t\t" + product.getName());
            System.out.println("Price:\t\t" + product.getPrice());
            System.out.println("Department:\t" + product.getDepartment() + "\n");
        }
    }
}
