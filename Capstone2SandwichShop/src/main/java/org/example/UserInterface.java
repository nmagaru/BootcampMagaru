package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static Order order;
    private static final List<String> TOPPING_TYPES = new ArrayList<>(
            Arrays.asList(
                    "meat",
                    "cheese",
                    "other toppings",
                    "sauce",
                    "sides"
            )
    );


    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int selectionHome;

        System.out.println("Welcome to DELI-cious!");
        while (true) {
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


    //processing functions
    private static void processOrderRequest() {
        Scanner scanner = new Scanner(System.in);
        int selectionOrder;

        while (true) {
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
        Scanner scanner = new Scanner(System.in);

        //prompt user for signature or from scratch
        System.out.print("Signature sandwich, or from scratch? (signature/scratch) ");
        String createInput = scanner.nextLine();

        if (createInput.equalsIgnoreCase("signature")) {
            System.out.println();
            String userSignature = OrderRepository.enterOption("signature");

            //prompt user for signature choice
            if (userSignature.equalsIgnoreCase("blt")) {
                BLT blt = new BLT();

                //update bread cost
                BigDecimal bltSizeBD = BigDecimal.valueOf(blt.getSize());
                blt.getBread().addToCost(bltSizeBD.multiply(BigDecimal.valueOf(1.50)));

                //pass in existing toppings, add all updated toppings to list
                List<Topping> newToppings = new ArrayList<>();
                for (String type : TOPPING_TYPES) {
                    newToppings.addAll(OrderRepository.updateToppings(
                            type, blt.getToppings(), true
                    ));
                }

                //rewrite existing toppings
                blt.clearToppings();
                newToppings.forEach(blt::addTopping);

                order.addSandwich(blt);
            }
            else if (userSignature.equalsIgnoreCase("philly cheesesteak")) {
                PhillyCheeseSteak philly = new PhillyCheeseSteak();

                //update bread cost
                BigDecimal phillySizeBD = BigDecimal.valueOf(philly.getSize());
                philly.getBread().addToCost(phillySizeBD.multiply(BigDecimal.valueOf(1.50)));

                //pass in existing toppings, add all updated toppings to list
                List<Topping> newToppings = new ArrayList<>();
                for (String type : TOPPING_TYPES) {
                    newToppings.addAll(OrderRepository.updateToppings(
                            type, philly.getToppings(), true
                    ));
                }

                //rewrite existing toppings
                philly.clearToppings();
                newToppings.forEach(philly::addTopping);

                order.addSandwich(philly);
            }
        }
        else if (createInput.equalsIgnoreCase("scratch")) {
            //create bread topping
            String userBread = OrderRepository.enterOption("bread");
            Topping bread = new Topping("bread", userBread);

            //set sandwich size, update bread cost
            int userSize = OrderRepository.enterSize();
            BigDecimal userSizeBD = BigDecimal.valueOf(userSize);
            bread.addToCost(userSizeBD.multiply(BigDecimal.valueOf(1.50)));

            boolean userToasted = OrderRepository.enterToasted();

            //create sandwich from inputted values
            Sandwich sandwich = new Sandwich(bread, userSize, userToasted);

            //add toppings to sandwich
            System.out.println("\n[Toppings]");
            for (String type : TOPPING_TYPES) {
                OrderRepository.updateToppings(
                        type, null, false
                ).forEach(sandwich::addTopping);
            }

            order.addSandwich(sandwich);
        }
        else {
            System.out.println("Please type one of the previous options.\n");
            return;
        }


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
