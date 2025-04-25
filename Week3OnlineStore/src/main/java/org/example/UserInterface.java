package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //OPTIONAL: handle all menus
    public static void displayMenu(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        int selection;

        //infinite loop to handle store function unless specified
        while(true) {
            //store home screen
            System.out.println("Welcome to the store! Choose an option:");
            System.out.println("1. View all products");
            System.out.println("2. Search by SKU");
            System.out.println("3. Search by price range");
            System.out.println("4. Search by name");
            System.out.println("5. Add to cart");
            System.out.println("6. Remove from cart");
            System.out.println("7. View cart");
            System.out.println("8. Checkout");
            System.out.println("9. Exit");
            System.out.print("Your selection: ");

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
                    //display all products
                    ProductRepository.displayProducts(products);
                    break;
                case 2:
                    //search by sku
                    break;
                case 3:
                    //search by price range
                    break;
                case 4:
                    //search by name
                    break;
                case 5:
                    //add to cart
                    break;
                case 6:
                    //remove from cart
                    break;
                case 7:
                    //view cart
                    break;
                case 8:
                    //checkout
                    break;
                case 9:
                    //exit application
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }
}
