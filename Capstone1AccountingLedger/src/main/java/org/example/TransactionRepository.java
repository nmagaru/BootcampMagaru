package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransactionRepository {
    public static LocalDate getDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (date.equalsIgnoreCase("today")) {
            return LocalDate.now();
        }
        else {
            //check if input is correct date format
            try {
                return LocalDate.parse(date, dateFormatter);
            } catch (Exception ex) {
                System.out.println("Date is incorrectly formatted or is invalid.");
                return null;
            }
        }
    }

    public static LocalTime getTime(String time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        if (time.equalsIgnoreCase("now")) {
            return LocalTime.now().withNano(0);
        }
        else {
            //check if input is correct date format
            try {
                return LocalTime.parse(time, timeFormatter);
            } catch (Exception ex) {
                System.out.println("Time is incorrectly formatted or is invalid.");
                return null;
            }
        }
    }

    public static void addTransaction(
            LocalDate date,
            LocalTime time,
            String description,
            String vendor,
            double amount) {
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        FileManager.writeTransaction(transaction);
    }
}
