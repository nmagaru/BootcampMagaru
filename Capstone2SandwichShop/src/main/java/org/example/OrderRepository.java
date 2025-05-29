package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderRepository {
    //displays
    private static void displayBread() {
        System.out.println("Bread\t-\t$5.50 base / +$1.50 per size up");
        System.out.println("White\t|\tWheat\t|\tRye\t\t|\tWrap");
    }

    private static void displayMeats() {
        System.out.println("Meats\t\t-\t$1.00 base / +$1.00 per size up");
        System.out.println("Extra Meat\t-\t$0.50 base per extra / +$0.50 per size up");
        System.out.println("Steak\t|\tHam\t\t|\tSalami");
        System.out.println("Chicken\t|\tBacon\t|\tRoast Beef");
    }

    private static void displayCheese() {
        System.out.println("Cheese\t\t\t-\t$0.75 base / +$0.75 per size up");
        System.out.println("Extra Cheese\t-\t$0.30 base per extra / +$0.30 per size up");
        System.out.println("American\t|\tProvolone\t|\tCheddar\t\t|\tSwiss");
    }

    private static void displayOther() {
        System.out.println("Other Toppings\t-\tIncluded in sandwich price");
        System.out.println("Lettuce\t\t|\tPeppers\t\t|\tOnions");
        System.out.println("Tomatoes\t|\tJalapenos\t|\tCucumbers");
        System.out.println("Pickles\t\t|\tGuacamole\t|\tMushrooms");
    }

    private static void displaySauces() {
        System.out.println("Sauces\t-\tIncluded in sandwich price");
        System.out.println("Mayo\t|\tMustard\t\t|\tKetchup");
        System.out.println("Ranch\t|\tVinaigrette\t|\tThousand Island");
    }

    private static void displaySides() {
        System.out.println("Sides\t-\tIncluded in sandwich price");
        System.out.println("Au Jus\t|\tSauce");
    }

    private static void displayDrinks() {
        System.out.println("Drinks\t-\t$2.00 base / +$0.50 per size up");
        System.out.println("Coke\t|\tSprite\t\t|\tDiet Coke");
        System.out.println("Juice\t|\tLemonade\t|\tWater");
    }

    private static void displayChips() {
        System.out.println("Chips\t-\t$1.50");
        System.out.println("Lays\t|\tDoritos\t\t|\tRuffles\t\t|\tMiss Vickies");
    }

    private static void displaySignatures() {
        System.out.println("Signatures");
        System.out.println("BLT\t\t|\tPhilly Cheesesteak");
    }


    //processing functions
    //used for smaller, simpler selections
    public static String enterOption(String optionType) {
        Scanner scanner = new Scanner(System.in);
        String optionInput;

        while (true) {
            if (optionType.equalsIgnoreCase("bread")) {
                displayBread();
            }
            else if (optionType.equalsIgnoreCase("drink")) {
                displayDrinks();
            }
            else if (optionType.equalsIgnoreCase("chips")) {
                displayChips();
            }
            else if (optionType.equalsIgnoreCase("signature")) {
                displaySignatures();
            }

            System.out.printf("Choose your %s: ", optionType);
            optionInput = scanner.nextLine();

            //check choices file if choice is valid
            if (FileManager.findChoice(optionType, optionInput)) {
                System.out.println();
                return optionInput;
            }

            System.out.println("Please type one of the above options.\n");
        }
    }

    //used for size specifically due to int results
    public static int enterSize() {
        Scanner scanner = new Scanner(System.in);
        String sizeInput;

        while (true) {
            System.out.println("Sizes");
            System.out.println("Small\t|\tMedium\t|\tLarge");

            System.out.print("Choose your size: ");
            sizeInput = scanner.nextLine();

            switch (sizeInput.toLowerCase()) {
                case "small":
                    System.out.println();
                    return 0;
                case "medium":
                    System.out.println();
                    return 1;
                case "large":
                    System.out.println();
                    return 2;
                default:
                    System.out.println("Please type one of the above options.\n");
                    break;
            }
        }
    }

    //used for specifically toasted due to boolean results
    public static boolean enterToasted() {
        Scanner scanner = new Scanner(System.in);
        String toastedInput;

        while (true) {
            System.out.print("Want your sandwich toasted? (Y/N) ");
            toastedInput = scanner.nextLine();

            switch (toastedInput.toUpperCase()) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Please type one of the previous options.\n");
                    break;
            }
        }
    }

    //used for topping management
    public static List<Topping> updateToppings(
            String toppingType,
            List<Topping> oldToppings,
            boolean isSignature
    ) {
        Scanner scanner = new Scanner(System.in);
        String toppingInput, updateInput;
        List<Topping> toppings = new ArrayList<>();
        Topping topping;
        boolean isRemoved;

        //handle one topping type at a time
        if (isSignature) {
            for (Topping searchTopping : oldToppings) {
                if (toppingType.equalsIgnoreCase(searchTopping.getType())) {
                    toppings.add(searchTopping);
                }
            }
        }

        while (true) {
            isRemoved = false;

            //prompt user if adding or removing (adding only if from scratch)
            if (isSignature) {
                System.out.printf("Add or Remove %s? ( add / remove / continue ) ", toppingType);
                updateInput = scanner.nextLine();

                switch (updateInput.toLowerCase()) {
                    case "add":
                        updateInput = "add";
                        break;
                    case "remove":
                        updateInput = "remove";
                        break;
                    case "continue":
                        System.out.println();
                        return toppings;
                    default:
                        System.out.println("Please type one of the previous options.\n");
                        continue;
                }
            }
            else {
                System.out.printf("Add %s? (Y/N) ", toppingType);
                updateInput = scanner.nextLine();

                switch (updateInput.toUpperCase()) {
                    case "Y":
                        break;
                    case "N":
                        System.out.println();
                        return toppings;
                    default:
                        System.out.println("Please type one of the previous options.\n");
                        continue;
                }
            }

            //display ingredients based on type
            if (updateInput.equalsIgnoreCase("add") || !isSignature) {
                switch (toppingType) {
                    case "meat":
                        displayMeats();
                        break;
                    case "cheese":
                        displayCheese();
                        break;
                    case "other toppings":
                        displayOther();
                        break;
                    case "sauce":
                        displaySauces();
                        break;
                    case "sides":
                        displaySides();
                        break;
                    default:
                        break;
                }
            }

            System.out.printf("Choose your %s: ", toppingType);
            toppingInput = scanner.nextLine();

            //check choices file if choice is valid
            if (FileManager.findChoice(toppingType, toppingInput) ) {
                if (updateInput.equalsIgnoreCase("add") || !isSignature) {
                    topping = new Topping(toppingType, toppingInput);
                    toppings.add(topping);
                }
                else {
                    for (Topping searchTopping : toppings) {
                        if (toppingInput.equalsIgnoreCase(searchTopping.getName())) {
                            toppings.remove(searchTopping);
                            isRemoved = true;
                            break;
                        }
                    }

                    if (isRemoved) {
                        System.out.println(toppingInput + " removed.");
                    }
                    else {
                        System.out.println("Topping not found.");
                    }
                }
                System.out.println();
            }
            else {
                System.out.println("Please type one of the above options.\n");
            }
        }
    }


    //display result functions (ui and receipt file)
    public static String writeSandwiches(Order order) {
        List<Sandwich> sandwiches = order.getSandwiches();
        String sandwichSize = "";
        String sandwichToasted = "";
        String currentToppingType = "";
        String returnString = "";

        if (!sandwiches.isEmpty()) {
            for (int i = 0; i < sandwiches.size(); i++) {
                //set size according to ints
                switch (sandwiches.get(i).getSize()) {
                    case 0:
                        sandwichSize = "4\"";
                        break;
                    case 1:
                        sandwichSize = "8\"";
                        break;
                    case 2:
                        sandwichSize = "12\"";
                        break;
                    default:
                        break;
                }

                if (sandwiches.get(i).isToasted()) {
                    sandwichToasted = "yes";
                }
                else {
                    sandwichToasted = "no";
                }

                //add topping costs to total cost
                BigDecimal sandwichCost = BigDecimal.valueOf(0);
                sandwichCost = sandwichCost.add(sandwiches.get(i).getBread().getCost());
                for (Topping topping : sandwiches.get(i).getToppings()) {
                    sandwichCost = sandwichCost.add(topping.getCost());
                }
                String sandwichCostString = String.format("%.2f", sandwichCost);

                //create display string
                returnString +=
                        "Sandwich #" + (i + 1) + " - $" + sandwichCostString + "\n" +
                        "\tSize:\t\t" + sandwichSize + "\n" +
                        "\tBread:\t\t" + sandwiches.get(i).getBread().getName() + "\n" +
                        "\tToasted?:\t" + sandwichToasted + "\n" +
                        "\tToppings:" +  "\n";

                for (Topping topping : sandwiches.get(i).getToppings()) {
                    //separate topping display by type
                    if (!currentToppingType.equalsIgnoreCase(topping.getType())) {
                        currentToppingType = topping.getType();
                        returnString += "\t\t" + currentToppingType + ":\n";
                    }

                    returnString += "\t\t\t" +
                            "" + topping.getName() + "\n";
                }
            }

            return returnString;
        }
        else {
            return "No sandwiches were purchased.\n";
        }
    }

    public static String writeDrinks(Order order) {
        List<Drink> drinks = order.getDrinks();
        String drinkSize = "";
        String returnString = "";

        if (!drinks.isEmpty()) {
            //set size according to ints
            for (int i = 0; i < drinks.size(); i++) {
                switch (drinks.get(i).getSize()) {
                    case 0:
                        drinkSize = "small";
                        break;
                    case 1:
                        drinkSize = "medium";
                        break;
                    case 2:
                        drinkSize = "large";
                        break;
                    default:
                        break;
                }

                BigDecimal drinkCost = drinks.get(i).getCost();
                String drinkCostString = String.format("%.2f", drinkCost);

                //create display string
                returnString +=
                        "Drink #" + (i + 1) + " - $" + drinkCostString + "\n" +
                        "\tSize:\t" + drinkSize + "\n" +
                        "\tFlavor:\t" + drinks.get(i).getFlavor() + "\n";
            }

            return returnString;
        }
        else {
            return "No drinks were purchased.\n";
        }
    }

    public static String writeChips(Order order) {
        List<String> chips = order.getChips();
        String returnString = "";

        if (!chips.isEmpty()) {
            //all chips are the same price, regardless of size
            BigDecimal chipsCost = BigDecimal.valueOf(chips.size() * 1.50);
            String chipsCostString = String.format("%.2f", chipsCost);

            //create return string
            returnString += "Chips - $" + chipsCostString + "\n";
            for (String chip : chips) {
                returnString += "\t" + chip + "\n";
            }

            return returnString;
        }
        else {
            return "No chips were purchased.\n";
        }
    }
}
