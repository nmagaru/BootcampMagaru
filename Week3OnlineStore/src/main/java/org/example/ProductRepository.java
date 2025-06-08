package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    //OPTIONAL:
    //filtering functions
    //return product data
    private static List<Product> products = FileLoader.readFile();

    public static List<Product> getProducts() {
        return products;
    }

    //display all products from a list
    public static void displayProducts(List<Product> productList) {
        //print sku, name, price, department
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    //search methods
    //by SKU
    public static Product findBySKU(String sku) {
        for (Product product : products) {
            if (sku.equalsIgnoreCase(product.getSku())) {
                return product;
            }
        }

        return null;
    }

    //by name or price range
    public static List<Product> findByProperty(String search, double min, double max, String searchType) {
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

        return matches;
    }
}
