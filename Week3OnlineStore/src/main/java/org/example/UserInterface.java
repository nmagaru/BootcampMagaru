package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //OPTIONAL: handle all menus
    public static void displayMenu(List<Product> products, ShoppingCart cart) {
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
                    System.out.print("Search for this product SKU: ");
                    String searchSKU = scanner.nextLine();

                    List<Product> skuResult = ProductRepository.findByProperty(products, searchSKU, 0, 0, "sku");
                    ProductRepository.displayProducts(skuResult);
                    break;
                case 3:
                    //search by price range
                    System.out.println("Search within these product prices:");
                    System.out.print("Minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();

                    List<Product> priceResult = ProductRepository.findByProperty(products, "", minPrice, maxPrice, "price");
                    ProductRepository.displayProducts(priceResult);
                    break;
                case 4:
                    //search by name
                    System.out.print("Search for this product name: ");
                    String searchName = scanner.nextLine();

                    List<Product> nameResult = ProductRepository.findByProperty(products, searchName, 0, 0, "name");
                    ProductRepository.displayProducts(nameResult);
                    break;
                case 5:
                    //add to cart
                    //TODO: which property is it using?
                    System.out.println("Enter ??? ");
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
