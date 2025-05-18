package org.example;

import java.io.*;

public class DealershipFileManager {
    public static Dealership getDealership() {
        String input;
        String[] properties;

        try {
            //System.out.println("Loading csv file...");

            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //read dealership information
            input = bufferedReader.readLine();
            properties = input.split("\\|");
            Dealership dealership = new Dealership(properties[0], properties[1], properties[2]);

            //read vehicle information
            while ((input = bufferedReader.readLine()) != null) {
                properties = input.split("\\|");

                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(properties[0]),
                        Integer.parseInt(properties[1]),
                        properties[2],
                        properties[3],
                        properties[4],
                        properties[5],
                        Integer.parseInt(properties[6]),
                        Double.parseDouble(properties[7])
                );

                dealership.addVehicle(vehicle);
            }

            bufferedReader.close();

            return dealership;
        }
        catch(IOException ex) {
            System.out.println("Failed to load csv file.");
            ex.printStackTrace();
            return null;
        }
    }

    public static void saveDealership(Dealership dealership) {
        String filePath = "src/main/resources/inventory.csv";
        File file = new File(filePath);

        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if (file.delete()) {
                try {
                    file.createNewFile();
                }
                catch(IOException ex) {
                    System.out.println("File could not be created.");
                    ex.printStackTrace();;
                }
            }

            FileWriter writer = new FileWriter(file, true);

            writer.write(
                dealership.getName() + "|" +
                    dealership.getAddress() + "|" +
                    dealership.getPhone() + "\n"
            );

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.toString() + "\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("File could not be recreated.");
            e.printStackTrace();
        }
    }
}
