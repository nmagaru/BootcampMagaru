package org.example;

import java.util.Scanner;

public class FutureValueCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your initial deposit: ");
        double deposit = scanner.nextDouble(); //P

        System.out.print("Enter your annual interest rate as a percentage: ");
        double rateAnnual = scanner.nextDouble() / 100; //r

        System.out.print("Enter your compounding length in years: ");
        int lengthYears = scanner.nextInt(); //t

        //calculate daily interest rate: daily = annual / 365
        //double rateDaily = rateAnnual / 365;

        //calculate deposit future value: value = P(1 + r/n)^(n*t)
        double futureValue = deposit *
                (Math.pow(1 + (rateAnnual / 365), 365 * lengthYears));

        //calculate total interest earned:  interest = future value - deposit
        double interestEarned = futureValue - deposit;

        //print calculation result
        System.out.printf("\nFuture value: $%.2f\nTotal interest earned: $%.2f", futureValue, interestEarned);
    }
}
