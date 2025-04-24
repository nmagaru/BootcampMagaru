package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input;
        List<Product> productList = new ArrayList<>();

        //open and read through csv file
        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            while ((input = bufferedReader.readLine()) != null) {
                String[] productProperties = input.split("\\|");

                Product currentProduct = new Product(
                        productProperties[0],
                        productProperties[1],
                        Double.parseDouble(productProperties[2]),
                        productProperties[3]
                );

                productList.add(currentProduct);
            }

            bufferedReader.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }


        Scanner scanner = new Scanner(System.in);


        while(true) {
            //store home screen
            System.out.println(
                    "Welcome to the online store! Please select an option below:" +
                    "\n(1) Display and buy products" +
                    "\n(2) Display cart" +
                    "\n(3) Exit application\n"
            );
            System.out.print("Your selection: ");

            int selection;
            //check if user selection is an integer, reopen home screen if not
            try {
                String selectionAsString = scanner.nextLine();
                selection = Integer.parseInt(selectionAsString);
            }
            catch (Exception ex) {
                System.out.println("Invalid input, please type the number listed for your option.\n");
                continue;
            }

            switch (selection) {
                case 1:
                    //display products screen
                    break;
                case 2:
                    //display cart screen
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}