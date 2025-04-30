package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
