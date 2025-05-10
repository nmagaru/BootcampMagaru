package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;


    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.inventory = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> returnVehicles = new ArrayList<>();
        double currentPrice;

        for (Vehicle vehicle : inventory) {
            currentPrice = vehicle.getPrice();

            if (currentPrice >= min && currentPrice <= max) {
                returnVehicles.add(vehicle);
            }
        }

        return returnVehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> returnVehicles = new ArrayList<>();
        String currentMake, currentModel;


        for (Vehicle vehicle : inventory) {
            currentMake = vehicle.getMake();
            currentModel = vehicle.getModel();

            if (make.equalsIgnoreCase(currentMake) && model.equalsIgnoreCase(currentModel)) {
                returnVehicles.add(vehicle);
            }
        }

        return returnVehicles;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> returnVehicles = new ArrayList<>();
        int currentYear;

        for (Vehicle vehicle : inventory) {
            currentYear = vehicle.getYear();

            if (currentYear >= min && currentYear <= max) {
                returnVehicles.add(vehicle);
            }
        }

        return returnVehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> returnVehicles = new ArrayList<>();
        String currentColor;

        for (Vehicle vehicle : inventory) {
            currentColor = vehicle.getColor();

            if (color.equalsIgnoreCase(currentColor)) {
                returnVehicles.add(vehicle);
            }
        }

        return returnVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> returnVehicles = new ArrayList<>();
        int currentMileage;

        for (Vehicle vehicle : inventory) {
            currentMileage = vehicle.getOdometer();

            if (currentMileage >= min && currentMileage <= max) {
                returnVehicles.add(vehicle);
            }
        }

        return returnVehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> returnVehicles = new ArrayList<>();
        String currentType;

        for (Vehicle vehicle : inventory) {
            currentType = vehicle.getVehicleType();

            if (vehicleType.equalsIgnoreCase(currentType)) {
                returnVehicles.add(vehicle);
            }
        }

        return returnVehicles;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {

    }
}
