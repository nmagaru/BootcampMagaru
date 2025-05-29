package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static Order order;


    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int selectionHome;

        //infinite loop to handle functionality
        System.out.println("Welcome to DELI-cious!");
        while (true) {
            //home screen
            displayHomeScreen();

            //check if user selection is an integer
            try {
                selectionHome = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("Invalid input, please type a listed number.\n");
                continue;
            }

            switch (selectionHome) {
                case 1:
                    System.out.println();
                    init();
                    processOrderRequest();
                    break;
                case 0:
                    System.out.println("Quitting...\n");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                default:
                    System.out.println("Invalid input, please type a listed number.\n");
                    break;
            }
        }
    }


    //helper functions
    //initialize/reset order
    private static void init() {
        order = new Order();
    }

    //displays
    private static void displayHomeScreen() {
        System.out.println("Home Screen");
        System.out.println("----------------------------");
        System.out.println("1.\tNew Order");
        System.out.println("0.\tExit Application");
        System.out.print("Choose an option: ");
    }

    private static void displayOrderScreen() {
        System.out.println("Order Screen");
        System.out.println("----------------------------");
        System.out.println("1.\tAdd Sandwich");
        System.out.println("2.\tAdd Drink");
        System.out.println("3.\tAdd Chips");
        System.out.println("4.\tCheckout");
        System.out.println("0.\tCancel Order");
        System.out.print("Choose an option: ");
    }


    //processing methods
    private static void processOrderRequest() {
        Scanner scanner = new Scanner(System.in);
        int selectionOrder;

        //infinite loop to handle functionality
        while (true) {
            //order screen
            displayOrderScreen();

            //check if user selection is an integer
            try {
                selectionOrder = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("Invalid input, please type a listed number.\n");
                continue;
            }

            switch (selectionOrder) {
                case 1:
                    System.out.println();
                    processSandwichRequest();
                    break;
                case 2:
                    System.out.println();
                    processDrinkRequest();
                    break;
                case 3:
                    System.out.println();
                    processChipsRequest();
                    break;
                case 4:
                    System.out.println();
                    processCheckoutRequest();
                    break;
                case 0:
                    System.out.println("Deleting order, returning to home screen...\n");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                default:
                    break;
            }
        }
    }

    private static void processSandwichRequest() {
        System.out.println("Create Your Sandwich");
        System.out.println("----------------------------");

        //create bread topping
        String userBread = OrderRepository.enterOption("bread");
        Topping bread = new Topping("bread", userBread);

        //set sandwich size, update bread cost
        int userSize = OrderRepository.enterSize();
        BigDecimal userSizeBD = BigDecimal.valueOf(userSize);
        bread.addToCost(userSizeBD.multiply(BigDecimal.valueOf(1.50)));

        //set toasted or not
        boolean userToasted = OrderRepository.enterToasted();

        //create sandwich from inputted values
        Sandwich sandwich = new Sandwich(bread, userSize, userToasted);

        //add toppings to sandwich
        System.out.println("\n[Toppings]");
        OrderRepository.enterToppings("meat")
                .forEach(sandwich::addTopping);
        OrderRepository.enterToppings("cheese")
                .forEach(sandwich::addTopping);
        OrderRepository.enterToppings("other toppings")
                .forEach(sandwich::addTopping);
        OrderRepository.enterToppings("sauce")
                .forEach(sandwich::addTopping);
        OrderRepository.enterToppings("sides")
                .forEach(sandwich::addTopping);
        order.addSandwich(sandwich);
    }

    private static void processDrinkRequest() {
        System.out.println("Choose Your Drink");
        System.out.println("----------------------------");

        //get drink choice
        int userSize = OrderRepository.enterSize();
        String userDrink = OrderRepository.enterOption("drink");

        Drink drink = new Drink(userSize, userDrink);
        order.addDrink(drink);
    }

    private static void processChipsRequest() {
        System.out.println("Choose Your Chips");
        System.out.println("----------------------------");

        //get chips choice
        String userChips = OrderRepository.enterOption("chips");

        order.addChips(userChips);
    }

    private static void processCheckoutRequest() {
        System.out.println("Checkout");
        System.out.println("----------------------------");

        System.out.println(OrderRepository.writeSandwiches(order));
        System.out.println(OrderRepository.writeDrinks(order));
        System.out.println(OrderRepository.writeChips(order));

        order.calculateTotal();
        System.out.printf("Total Price: $%.2f\n\n", order.getTotal());

        FileManager.createReceipt(order);

        init();
    }
}
