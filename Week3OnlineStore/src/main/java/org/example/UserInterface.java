package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static List<Product> products;
    private static ShoppingCart cart;


    //OPTIONAL: handle all menus
    public static void displayMenu() {
        products = FileLoader.readFile();
        cart = new ShoppingCart();

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
                    System.out.print("Search by product SKU: ");
                    String searchSKU = scanner.nextLine();

                    Product skuResult = ProductRepository.findBySKU(products, searchSKU);
                    if (skuResult != null) {
                        System.out.println(skuResult.toString());
                    }
                    else {
                        System.out.println("Product not found.\n");
                    }

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
                    if (!priceResult.isEmpty()) {
                        ProductRepository.displayProducts(priceResult);
                    }
                    else {
                        System.out.println("No products were found.\n");
                    }

                    break;
                case 4:
                    //search by name
                    System.out.print("Search by product name: ");
                    String searchName = scanner.nextLine();

                    List<Product> nameResult = ProductRepository.findByProperty(products, searchName, 0, 0, "name");
                    if (!nameResult.isEmpty()) {
                        ProductRepository.displayProducts(nameResult);
                    }
                    else {
                        System.out.println("No products were found.\n");
                    }
                    break;
                case 5:
                    //add to cart
                    System.out.print("Enter the SKU of the product you want to add to your cart: ");
                    String purchaseSKU = scanner.nextLine();
                    boolean productFound = false;

                    for (Product product : products) {
                        if (purchaseSKU.equalsIgnoreCase(product.getSku())) {
                            productFound = true;
                            cart.addProductToCart(product);
                            System.out.println(product.getName() + " successfully added to cart.\n");
                        }
                    }

                    if (!productFound) {
                        System.out.println("Product not found.\n");
                    }
                    break;
                case 6:
                    //remove from cart
                    System.out.print("Enter the SKU of the product you want to remove from your cart: ");
                    String removeSKU = scanner.nextLine();

                    cart.removeProductFromCart(removeSKU);
                    break;
                case 7:
                    //view cart
                    cart.displayCart();
                    break;
                case 8:
                    //checkout
                    System.out.println("Your cart total is: " + cart.getCartTotal());
                    System.out.println("Thank you for shopping with us!\n");

                    cart.clearCart();
                    //TODO: optional objectives?
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
