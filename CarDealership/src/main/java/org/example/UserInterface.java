package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserInterface {
    private Dealership dealership;
    private String dbUserName;
    private String dbPassword;
    private String connectionString = "jdbc:mysql://localhost:3306/cardealershipdatabase";
    private VehicleDao vehicleDao;


    public UserInterface(String dbUserName, String dbPassword) {
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
    }


    public void display() {
        init();

        Scanner scanner = new Scanner(System.in);
        int selection;
        String userPassword, password = "drowssap";

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
                case 10:
                    processSellLeaseVehicleRequest();
                    break;
                case 99:
                    System.out.println("Quitting...");
                    System.exit(0);
                case 100:
                    System.out.print("Enter your admin password: ");
                    userPassword = scanner.nextLine();

                    if (userPassword.equals(password)) {
                        AdminUserInterface.display();
                    }
                    else {
                        System.out.println("Incorrect password.\n");
                    }

                    break;
                default:
                    break;
            }
        }
    }


    private void init() {
        dealership = DealershipFileManager.getDealership();

        vehicleDao = new VehicleDao(connectionString, dbUserName, dbPassword);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
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
                        "\nPrice\t\t-\t" + String.format("%.02f", vehicle.getPrice())
                );
            }
        }
    }

    private void displayMenu() {
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
        System.out.println("10 - Sell/Lease a vehicle");
        System.out.println("99 - Quit");
        System.out.println("100 - Admin commands");
        System.out.print("Your selection: ");
    }

    private void processGetByPriceRequest() {
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

//        displayVehicles(dealership.getVehiclesByPrice(userPriceMin, userPriceMax));
        List<String> priceSearch = Arrays
                .asList(String.valueOf(userPriceMin), String.valueOf(userPriceMax));
        displayVehicles(vehicleDao.getVehicles(priceSearch, "price"));
    }

    private void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        String userMake, userModel;

        System.out.print("\nEnter a make: ");
        userMake = scanner.nextLine();
        System.out.print("Enter a model: ");
        userModel = scanner.nextLine();

//        displayVehicles(dealership.getVehiclesByMakeModel(userMake, userModel));
        List<String> makeModelSearch = Arrays.asList(userMake, userModel);
        displayVehicles(vehicleDao.getVehicles(makeModelSearch, "make/model"));
    }

    private void processGetByYearRequest() {
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

//        displayVehicles(dealership.getVehiclesByYear(userYearMin, userYearMax));
        List<String> yearSearch = Arrays
                .asList(String.valueOf(userYearMin), String.valueOf(userYearMax));
        displayVehicles(vehicleDao.getVehicles(yearSearch, "year"));
    }

    private void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        String userColor;

        System.out.print("\nEnter a color: ");
        userColor = scanner.nextLine();

//        displayVehicles(dealership.getVehiclesByColor(userColor));
        List<String> colorSearch = Collections.singletonList(userColor);
        displayVehicles(vehicleDao.getVehicles(colorSearch, "color"));
    }

    private void processGetByMileageRequest() {
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

//        displayVehicles(dealership.getVehiclesByMileage(userMileageMin, userMileageMax));
        List<String> mileageSearch = Arrays
                .asList(String.valueOf(userMileageMin), String.valueOf(userMileageMax));
        displayVehicles(vehicleDao.getVehicles(mileageSearch, "mileage"));
    }

    private void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        String userVehicleType;

        System.out.print("\nEnter a vehicle type: ");
        userVehicleType = scanner.nextLine();

//        displayVehicles(dealership.getVehiclesByType(userVehicleType));
        List<String> typeSearch = Collections.singletonList(userVehicleType);
        displayVehicles(vehicleDao.getVehicles(typeSearch, "type"));
    }

    private void processGetAllVehiclesRequest() {
//        (dealership.getAllVehicles());
        displayVehicles(vehicleDao.getVehicles(new ArrayList<>(), "all"));
    }

    private void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        int userVin, userYear, userMileage;
        String userMake, userModel, userVehicleType, userColor;
        double userPrice;

        try {
            System.out.print("\nEnter your vehicle's identification number: ");
            userVin = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter your vehicle's year: ");
            userYear = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter your vehicle's make: ");
            userMake = scanner.nextLine();
            System.out.print("Enter your vehicle's model: ");
            userModel = scanner.nextLine();
            System.out.print("Enter your vehicle's type: ");
            userVehicleType = scanner.nextLine();
            System.out.print("Enter your vehicle's color: ");
            userColor = scanner.nextLine();
            System.out.print("Enter your vehicle's mileage: ");
            userMileage = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter your vehicle's price: ");
            userPrice = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e) {
            System.out.println("Please enter a valid value corresponding to that vehicle property.\n");
            return;
        }

        Vehicle userVehicle = new Vehicle(userVin, userYear, userMake, userModel, userVehicleType, userColor, userMileage, userPrice);

//        dealership.addVehicle(userVehicle);
//        DealershipFileManager.saveDealership(dealership);
        vehicleDao.addVehicle(userVehicle);
    }

    private void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        int userRemoveVin;

        try {
            System.out.print("\nEnter your vehicle's identification number: ");
            userRemoveVin = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception ex) {
            System.out.println("Please enter a valid identification number.\n");
            return;
        }

//        Vehicle removeVehicle = dealership.getVehicleByVin(userRemoveVin);
//
//        if (removeVehicle != null) {
//            dealership.removeVehicle(removeVehicle);
//            DealershipFileManager.saveDealership(dealership);
//            System.out.println("Vehicle successfully removed.");
//        }
//        else {
//            System.out.println("Vehicle not found.\n");
//        }

        vehicleDao.removeVehicle(userRemoveVin);
    }

    private void processSellLeaseVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String todayString = today.format(dateFormatter);
        String userName, userEmail, userSellOrLease, userFinance;
        int userRemoveVin;
        boolean isFinanced;

        try {
            System.out.print("\nEnter your name: ");
            userName = scanner.nextLine();
            System.out.print("Enter your email: ");
            userEmail = scanner.nextLine();
            System.out.print("Enter the vehicle's identification number to sell/lease: ");
            userRemoveVin = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception ex) {
            System.out.println("Please enter a valid identification number.\n");
            return;
        }

        Vehicle sellLeaseVehicle = dealership.getVehicleByVin(userRemoveVin);

        if (sellLeaseVehicle != null) {
            System.out.print("Would you like to SELL or LEASE this vehicle? (Please type your selection): ");
            userSellOrLease = scanner.nextLine();

            if (userSellOrLease.equalsIgnoreCase("sell")) {
                System.out.print("Would you like to finance this vehicle? (Y/N): ");
                userFinance = scanner.nextLine();

                if (userFinance.equalsIgnoreCase("y")) {
                    isFinanced = true;
                }
                else if (userFinance.equalsIgnoreCase("n")) {
                    isFinanced = false;
                }
                else {
                    System.out.println("Please enter 'Y' or 'N'.\n");
                    return;
                }

                SalesContract salesContract = new SalesContract(
                        todayString,
                        userName,
                        userEmail,
                        sellLeaseVehicle,
                        isFinanced
                );

                System.out.println("Total price: " + String.format("%.2f", salesContract.getTotalPrice()));
                System.out.println("Monthly payment: " + String.format("%.2f", salesContract.getMonthlyPayment()));
                ContractDataManager.saveContract(salesContract);
            }
            else if (userSellOrLease.equalsIgnoreCase("lease")) {
                if (today.getYear() - sellLeaseVehicle.getYear() > 3) {
                    System.out.println("Vehicle is too old to lease. Please select another vehicle.\n");
                    return;
                }
                else {
                    LeaseContract leaseContract = new LeaseContract(
                            todayString,
                            userName,
                            userEmail,
                            sellLeaseVehicle
                    );

                    System.out.println("Total price: " + String.format("%.2f", leaseContract.getTotalPrice()));
                    System.out.println("Monthly payment: " + String.format("%.2f", leaseContract.getMonthlyPayment()));
                    ContractDataManager.saveContract(leaseContract);
                }
            }
            else {
                System.out.println("Please enter 'sell' or 'lease'.\n");
                return;
            }

            dealership.removeVehicle(sellLeaseVehicle);
            DealershipFileManager.saveDealership(dealership);
            System.out.println("Contract successfully created.\n");
        }
        else {
            System.out.println("Vehicle not found.\n");
            return;
        }
    }
}
