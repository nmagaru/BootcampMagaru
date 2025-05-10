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
                selection = Integer.parseInt(scanner.nextLine());
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
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles were found.\n");
        }
        else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(
                        "\nID Number\t-\t" + vehicle.getVin() +
                        "\nYear\t\t-\t" + vehicle.getYear() +
                        "\nMake\t\t-\t" + vehicle.getMake() +
                        "\nModel\t\t-\t" + vehicle.getModel() +
                        "\nType\t\t-\t" + vehicle.getVehicleType() +
                        "\nColor\t\t-\t" + vehicle.getColor() +
                        "\nMileage\t\t-\t" + vehicle.getOdometer() +
                        "\nPrice\t\t-\t" + vehicle.getPrice()
                );
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nChoose an option:");
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
        Scanner scanner = new Scanner(System.in);
        double userPriceMin, userPriceMax;

        try {
            System.out.print("\nEnter a minimum price: ");
            userPriceMin = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter a maximum price: ");
            userPriceMax = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception ex) {
            System.out.println("Please enter a valid USD amount.\n");
            return;
        }

        displayVehicles(dealership.getVehiclesByPrice(userPriceMin, userPriceMax));
    }

    private static void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        String userMake, userModel;

        System.out.print("\nEnter a make: ");
        userMake = scanner.nextLine();
        System.out.print("Enter a model: ");
        userModel = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(userMake, userModel));
    }

    private static void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        int userYearMin, userYearMax;

        try {
            System.out.print("\nEnter a minimum year: ");
            userYearMin = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter a maximum year: ");
            userYearMax = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception ex) {
            System.out.println("Please enter a valid year value.\n");
            return;
        }

        displayVehicles(dealership.getVehiclesByYear(userYearMin, userYearMax));
    }

    private static void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        String userColor;

        System.out.print("\nEnter a color: ");
        userColor = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(userColor));
    }

    private static void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        int userMileageMin, userMileageMax;

        try {
            System.out.print("\nEnter a minimum mileage: ");
            userMileageMin = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter a maximum mileage: ");
            userMileageMax = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception ex) {
            System.out.println("Please enter a valid mileage value.\n");
            return;
        }

        displayVehicles(dealership.getVehiclesByMileage(userMileageMin, userMileageMax));
    }

    private static void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        String userVehicleType;

        System.out.print("\nEnter a color: ");
        userVehicleType = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(userVehicleType));
    }

    private static void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private static void processAddVehicleRequest() {

    }

    private static void processRemoveVehicleRequest() {

    }
}
