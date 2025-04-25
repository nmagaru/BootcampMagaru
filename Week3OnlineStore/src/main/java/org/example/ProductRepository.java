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
            System.out.println(product.toString());
        }
    }

    //search methods
    //by SKU
    public static Product findBySKU(List<Product> products, String sku) {
        for (Product product : products) {
            if (sku.equalsIgnoreCase(product.getSku())) {
                return product;
            }
        }

        return null;
    }

    //by name or price range
    public static List<Product> findByProperty(List<Product> products, String search, double min, double max, String searchType) {
        List<Product> matches = new ArrayList<>();
        String searchLowercase = search.toLowerCase();

        for (Product product : products) {
            if (searchType.equalsIgnoreCase("name")) {
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

        if (!matches.isEmpty()) {
            return matches;
        }
        else {
            return null;
        }

    }
}
