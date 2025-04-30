package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class UserInterface {
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int selection;

        //infinite loop to handle functionality
        System.out.println("Your Accounting Ledger");
        while (true) {
            //home screen
            System.out.println("\nChoose an option:");
            System.out.println("1. Add deposit");
            System.out.println("2. Make a payment");
            System.out.println("3. View ledger");
            System.out.println("4. Exit");
            System.out.print("Your selection: ");

            //check if user selection is an integer, reopen home screen if not
            try {
                String selectionAsString = scanner.nextLine();
                selection = Integer.parseInt(selectionAsString);
            } catch (Exception ex) {
                System.out.println("Invalid input, please type the number listed for your option.\n");
                continue;
            }

            switch (selection) {
                case 1:
                    System.out.print("Enter your deposit amount: ");
                    double amount = 0.0;
                    try {
                        String amountAsString = scanner.nextLine();
                        amount = Double.parseDouble(amountAsString);
                    } catch (Exception ex) {
                        System.out.println("Invalid input, please type the amount in the form XX.XX.");
                        break;
                    }
                    if (amount < 0.0) {
                        System.out.println("Deposit amount must be of positive value.");
                        break;
                    }

                    System.out.print("Enter a deposit description: ");
                    String description = scanner.nextLine();

                    System.out.println("Enter the deposit vendor: ");
                    String vendor = scanner.nextLine();

                    TransactionRepository.addTransaction(description, vendor, amount);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
