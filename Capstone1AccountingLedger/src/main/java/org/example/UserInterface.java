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
                    //enter date
                    System.out.print("Enter the deposit date in the format 'yyyy-mm-dd', or 'today' for current date: ");
                    String depositDate = scanner.nextLine();
                    LocalDate parsedDepositDate = TransactionRepository.getDate(depositDate);
                    if (parsedDepositDate == null) {
                        break;
                    }

                    //enter time
                    System.out.print("Enter the deposit time in the format 'hh:mm:ss', or 'now' for current time: ");
                    String depositTime = scanner.nextLine();
                    LocalTime parsedDepositTime = TransactionRepository.getTime(depositTime);
                    if (parsedDepositTime == null) {
                        break;
                    }

                    //enter description
                    System.out.print("Enter a deposit description: ");
                    String depositDescription = scanner.nextLine();

                    //enter vendor
                    System.out.print("Enter the deposit vendor: ");
                    String depositVendor = scanner.nextLine();

                    //enter amount
                    System.out.print("Enter your deposit amount: ");
                    double depositAmount = 0.0;
                    //check if input is double
                    try {
                        String amountAsString = scanner.nextLine();
                        depositAmount = Double.parseDouble(amountAsString);
                    } catch (Exception ex) {
                        System.out.println("Invalid input, please type the amount in the format XX.XX.");
                        break;
                    }
                    //check if input is positive
                    if (depositAmount < 0.0) {
                        System.out.println("Deposit amount must be of positive value.");
                        break;
                    }

                    TransactionRepository.addTransaction(
                            parsedDepositDate,
                            parsedDepositTime,
                            depositDescription,
                            depositVendor,
                            depositAmount);
                    break;
                case 2:
                    //enter date
                    System.out.print("Enter the payment date in the format 'yyyy-mm-dd', or 'today' for current date: ");
                    String paymentDate = scanner.nextLine();
                    LocalDate parsedPaymentDate = TransactionRepository.getDate(paymentDate);
                    if (parsedPaymentDate == null) {
                        break;
                    }

                    //enter time
                    System.out.print("Enter the payment time in the format 'hh:mm:ss', or 'now' for current time: ");
                    String paymentTime = scanner.nextLine();
                    LocalTime parsedPaymentTime = TransactionRepository.getTime(paymentTime);
                    if (parsedPaymentTime == null) {
                        break;
                    }

                    //enter description
                    System.out.print("Enter a payment description: ");
                    String paymentDescription = scanner.nextLine();

                    //enter vendor
                    System.out.print("Enter the payment vendor: ");
                    String paymentVendor = scanner.nextLine();

                    //enter amount
                    System.out.print("Enter your payment amount: ");
                    double paymentAmount = 0.0;
                    //check if input is double
                    try {
                        String amountAsString = scanner.nextLine();
                        paymentAmount = Double.parseDouble(amountAsString);
                    } catch (Exception ex) {
                        System.out.println("Invalid input, please type the amount in the format XX.XX.");
                        break;
                    }
                    //check if input is positive, set negative if so
                    if (paymentAmount > 0.0) {
                        paymentAmount *= -1;
                    }

                    TransactionRepository.addTransaction(parsedPaymentDate, parsedPaymentTime, paymentDescription, paymentVendor, paymentAmount);
                    break;
                case 3:
                    System.out.println("Select a ledger display option: ");
                    System.out.println("ALL\t\t -\t All transactions");
                    System.out.println("DEPOSIT\t -\t All deposits");
                    System.out.println("PAYMENT\t -\t All payments");
                    System.out.print("Your selection: ");

                    String displaySelection = scanner.nextLine();
                    if (!LedgerRepository.displayTransactions(displaySelection)) {
                        System.out.println("Invalid input, please select one of the above values.");
                    }
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
