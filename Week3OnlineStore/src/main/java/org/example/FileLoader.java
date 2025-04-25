package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    public static List<Product> readFile() {
        String input;
        List<Product> productList = new ArrayList<>();

        //open and read through csv file
        try {
            System.out.println("Loading csv file");

            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            while ((input = bufferedReader.readLine()) != null) {
                String[] properties = input.split("\\|");

                Product currentProduct = new Product(
                        properties[0],
                        properties[1],
                        Double.parseDouble(properties[2]),
                        properties[3]
                );

                productList.add(currentProduct);
            }

            bufferedReader.close();

            return productList;
        }
        catch(IOException ex) {
            System.out.println("Failed to load csv file.");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
