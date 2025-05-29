package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    public static boolean findIngredient(String ingredientType, String search) {
        String input;
        List<String> ingredients;

        try {
            FileReader fileReader = new FileReader("src/main/resources/ingredients.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((input = bufferedReader.readLine()) != null) {
                ingredients = new ArrayList<String>(Arrays.asList(input.split("\\|")));

                if (ingredientType.equalsIgnoreCase(ingredients.get(0))) {
                    ingredients.remove(0);

                    bufferedReader.close();

                    return ingredients.stream().anyMatch(search::equalsIgnoreCase);
                }
            }

            return false;
        } catch(IOException ex) {
            System.out.println("Failed to load csv file.");
            ex.printStackTrace();
            return false;
        }
    }

    public static void createReceipt(Order order) {
        String filePath = "src/main/resources/Receipts/";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmmss");
        String today = LocalDate.now().format(dateFormatter);
        String now = LocalTime.now().format(timeFormatter);

        filePath += today + "-" + now + ".txt";
        File file = new File(filePath);

        //try creating new file to write to
        try {
            //check if folder exists
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //check if file exists, create new file if no
            //file should always be created, implemented like this just to check
            boolean fileExists = file.exists();
            if (!fileExists) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("Receipt created: " + file.getName() + "\n");
                    }
                } catch (IOException ex) {
                    System.out.println("File could not be created.\n");
                    ex.printStackTrace();
                }
            }

            //write to file
            FileWriter writer = new FileWriter(file, true);

            writer.write(OrderRepository.writeSandwiches(order));
            writer.write("\n");
            writer.write(OrderRepository.writeDrinks(order));
            writer.write("\n");
            writer.write(OrderRepository.writeChips(order));
            writer.write("\n");

            String totalString = String.format("%.2f", order.getTotal());
            writer.write("Total Price: $" + totalString + "\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Order could not be written to file.\n");
            e.printStackTrace();
        }
    }
}
