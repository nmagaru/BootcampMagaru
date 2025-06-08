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
    public static boolean findChoice(String choiceType, String search) {
        String input;
        List<String> choices;

        try {
            FileReader fileReader = new FileReader("src/main/resources/choices.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((input = bufferedReader.readLine()) != null) {
                choices = new ArrayList<>(Arrays.asList(input.split("\\|")));

                if (choiceType.equalsIgnoreCase(choices.get(0))) {
                    //remove choice type from search list
                    choices.remove(0);

                    bufferedReader.close();

                    return choices.stream().anyMatch(search::equalsIgnoreCase);
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

        //get current date/time, format for file name
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmmss");
        String today = LocalDate.now().format(dateFormatter);
        String now = LocalTime.now().format(timeFormatter);

        //create full file name
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


            DateTimeFormatter dateReceiptFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            DateTimeFormatter timeReceiptFormatter = DateTimeFormatter.ofPattern("hh:mm");
            String todayReceipt = LocalDate.now().format(dateReceiptFormatter);
            String nowReceipt = LocalTime.now().format(timeReceiptFormatter);
            writer.write("Date: " + todayReceipt + "\n");
            writer.write("Time: " + nowReceipt + "\n\n");

            //write functions modified to work for both ui and receipt file
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
