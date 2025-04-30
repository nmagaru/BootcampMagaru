package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionRepository {
    public static void addTransaction(
            String description,
            String vendor,
            double amount) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        FileManager.writeTransaction(transaction);
    }
}
