package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static Dealership dealership;


    public UserInterface() {}


    public static void display() {
        init();

        Scanner scanner = new Scanner(System.in);
        int selection;

        while(true) {
            displayMenu();

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
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    System.out.println("Quitting...");
                    System.exit(0);
                default:
                    break;
            }
        }
    }


    private static void init() {
        dealership = DealershipFileManager.getDealership();
    }

    private static void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(
                    "\nID Number\t-\t" + vehicle.getVin() +
                    "\nYear\t\t-\t" + vehicle.getYear() +
                    "\nMake\t\t-\t" + vehicle.getMake() +
                    "\nModel\t\t-\t" + vehicle.getModel() +
                    "\nType\t\t-\t" + vehicle.getVehicleType() +
                    "\nColor\t\t-\t" + vehicle.getColor() +
                    "\nMileage\t\t-\t" + vehicle.getOdometer() +
                    "\nPrice\t\t-\t" + vehicle.getPrice() + "\n"
            );
        }
    }

    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by vehicle type");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.print("Your selection: ");
    }

    private static void processGetByPriceRequest() {

    }

    private static void processGetByMakeModelRequest() {

    }

    private static void processGetByYearRequest() {

    }

    private static void processGetByColorRequest() {

    }

    private static void processGetByMileageRequest() {

    }

    private static void processGetByVehicleTypeRequest() {

    }

    private static void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private static void processAddVehicleRequest() {

    }

    private static void processRemoveVehicleRequest() {

    }
}
