package org.example;

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
}
