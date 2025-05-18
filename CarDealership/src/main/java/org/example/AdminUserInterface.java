package org.example;

import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    public static void display() {
        Scanner scanner = new Scanner(System.in);
        int selection;

        while(true) {
            displayAdminMenu();

            try {
                selection = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception ex) {
                System.out.println("Invalid input, please type the number listed for your option.\n");
                continue;
            }

            switch (selection) {
                case 1:
                    displayContracts(ContractDataManager.getContracts());
                    break;
                case 99:
                    System.out.println("Returning to user menu...\n");
                    return;
                default:
                    break;
            }
        }
    }


    private static void displayAdminMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - List ALL contracts");
        System.out.println("99 - Quit");
        System.out.print("Your selection: ");
    }

    private static void displayContracts(List<Contract> contracts) {
        if (contracts.isEmpty()) {
            System.out.println("No contracts were found.\n");
        }
        else {
            for (Contract contract : contracts) {
                System.out.println("-------------------------------------------");
                System.out.println("Owner Information");
                System.out.println(
                        "Name\t-\t" + contract.getCustomerName() +
                        "\nEmail\t-\t" + contract.getCustomerEmail()
                );
                System.out.println("\nVehicle Information");
                System.out.println(
                        "ID Number\t-\t" + contract.getVehicleSold().getVin() +
                        "\nYear\t\t-\t" + contract.getVehicleSold().getYear() +
                        "\nMake\t\t-\t" + contract.getVehicleSold().getMake() +
                        "\nModel\t\t-\t" + contract.getVehicleSold().getModel() +
                        "\nType\t\t-\t" + contract.getVehicleSold().getVehicleType() +
                        "\nColor\t\t-\t" + contract.getVehicleSold().getColor() +
                        "\nMileage\t\t-\t" + contract.getVehicleSold().getOdometer() +
                        "\nPrice\t\t-\t" + contract.getVehicleSold().getPrice()
                );
                if (contract instanceof SalesContract) {
                    System.out.println("\nSale Information");
                    System.out.println(
                            "Sales Tax\t\t-\t" + String.format("%.2f", ((SalesContract) contract).getSalesTax()) +
                            "\nRecording Fee\t-\t" + ((SalesContract) contract).getRecordingFee() +
                            "\nProcessing Fee\t-\t" + ((SalesContract) contract).getProcessingFee()
                    );

                    if (((SalesContract) contract).isFinanced()) {
                        System.out.println("Financed?\t\t-\tYES");
                    }
                    else {
                        System.out.println("Financed?\t\t-\tNO");
                    }
                }
                else if (contract instanceof LeaseContract) {
                    System.out.println("\nLease Information");
                    System.out.println(
                            "Expected Ending Price\t-\t" + String.format("%.2f", ((LeaseContract) contract).getExpEndingPrice()) +
                            "\nLease Fee\t\t\t\t-\t" + String.format("%.2f", ((LeaseContract) contract).getLeaseFee())
                    );
                }
                System.out.println("\nContract Information");
                System.out.println(
                        "Total Price\t\t-\t" + String.format("%.2f", contract.getTotalPrice()) +
                        "\nMonthly Payment\t-\t" + String.format("%.2f", contract.getMonthlyPayment())
                );
            }
        }
    }
}
