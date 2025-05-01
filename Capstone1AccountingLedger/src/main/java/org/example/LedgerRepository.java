package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LedgerRepository {
    public static boolean displayTransactions(String type) {
        List<Transaction> transactions = FileManager.readFile();
        boolean isDisplayed = false;

        if (transactions.isEmpty()) {
            System.out.println("No transactions were found.");
        }
        else {
            for (int i = transactions.size() - 1; i >= 0; i--) {
                if (type.equalsIgnoreCase("all")) {
                    System.out.println(transactions.get(i).toString());
                    isDisplayed = true;
                }
                else if (type.equalsIgnoreCase("deposit") &&
                        transactions.get(i).getAmount() > 0.0) {
                    System.out.println(transactions.get(i).toString());
                    isDisplayed = true;
                }
                else if (type.equalsIgnoreCase("payment") &&
                        transactions.get(i).getAmount() < 0.0) {
                    System.out.println(transactions.get(i).toString());
                    isDisplayed = true;
                }
            }
        }

        return isDisplayed;
    }

    public static List<Transaction> createReport(String type) {
        List<Transaction> transactions = FileManager.readFile();
        List<Transaction> reportList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate startDate = today, endDate = today;

        if (transactions.isEmpty()) {
            return reportList;
        }
        else {
            for (Transaction transaction : transactions) {
                LocalDate transactionDate = transaction.getDate();

                if (type.equalsIgnoreCase("mtd")) {
                    startDate = today.withDayOfMonth(1).minusDays(1);
                    endDate = today.plusDays(1);
                }
                else if (type.equalsIgnoreCase("prevmon")) {
                    startDate = today.minusMonths(1).withDayOfMonth(1).minusDays(1);
                    endDate = today.withDayOfMonth(1);
                }
                else if (type.equalsIgnoreCase("ytd")) {
                    startDate = today.withDayOfYear(1).minusDays(1);
                    endDate = today.plusDays(1);
                }
                else if (type.equalsIgnoreCase("prevyr")) {
                    startDate = today.minusYears(1).withDayOfYear(1).minusDays(1);
                    endDate = today.withDayOfYear(1);
                }

                if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate)) {
                    reportList.add(transaction);
                }
            }
        }

        return reportList;
    }
}
