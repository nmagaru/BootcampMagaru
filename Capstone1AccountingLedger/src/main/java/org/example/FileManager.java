package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void writeTransaction (Transaction transaction) {
        String filePath = "src/main/resources/transactions.csv";
        File file = new File(filePath);

        try {
            //check if folder exists
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //check if file exists, create new file if no
            boolean fileExists = file.exists();
            if (!fileExists) {
                try {
                    if (file.createNewFile()) {
                        //System.out.println("File created: " + file.getName());
                    }
                } catch (IOException ex) {
                    System.out.println("File could not be created.");
                    ex.printStackTrace();
                }
            }

            //check if file is empty
            boolean fileEmpty = file.length() == 0;
            FileWriter writer = new FileWriter(file, true);

            //write to file
            //write header if empty, write transaction if no
            if (fileEmpty) {
                writer.write("date|time|description|vendor|amount\n");
            }
            writer.write(transaction.toString() + "\n");

            //close writer
            writer.close();

        } catch (IOException ex) {
            System.out.println("Transaction could not be written to file.");
            ex.printStackTrace();
        }
    }

    public static List<Transaction> readFile() {
        String input;
        List<Transaction> transactionList = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        //open and read through csv file
        try {
            //System.out.println("Loading csv file");

            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            while ((input = bufferedReader.readLine()) != null) {
                String[] properties = input.split("\\|");

                Transaction currentTransaction = new Transaction(
                        LocalDate.parse(properties[0], dateFormatter),
                        LocalTime.parse(properties[1], timeFormatter),
                        properties[2],
                        properties[3],
                        Double.parseDouble(properties[4])
                );

                transactionList.add(currentTransaction);
            }

            bufferedReader.close();

            return transactionList;
        }
        catch(IOException ex) {
            System.out.println("Failed to load csv file.");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
