package org.example;

import java.util.ArrayList;
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

    //search method - by SKU, name, price range
    public static List<Product> findByProperty(List<Product> products, String search, double min, double max, String searchType) {
        List<Product> matches = new ArrayList<>();
        String searchLowercase = search.toLowerCase();

        for (Product product : products) {
            if (searchType.equalsIgnoreCase("sku")) {
                if (product.getSku().toLowerCase().contains(searchLowercase)) {
                    matches.add(product);
                }
            }
            else if (searchType.equalsIgnoreCase("name")) {
                if (product.getName().toLowerCase().contains(searchLowercase)) {
                    matches.add(product);
                }
            }
            else if (searchType.equalsIgnoreCase("price")) {
                if (product.getPrice() >= min && product.getPrice() <= max) {
                    matches.add(product);
                }
            }
        }

        return matches;
    }
}
