package org.example;

import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your principal amount: ");
        double principal = scanner.nextDouble(); //P

        System.out.print("Enter your annual interest rate as a percentage: ");
        double rateAnnual = scanner.nextDouble() / 100;

        System.out.print("Enter your loan length in years: ");
        int lengthYears = scanner.nextInt();

        //calculate monthly interest rate: monthly = annual / 12
        double rateMonthly = rateAnnual / 12 ; //r

        //calculate number of monthly payments: number = length * 12
        int lengthMonths = lengthYears * 12; //n

        //calculate monthly payment amount: M = P[r(1+r)^n] / [(1+r)^n-1]
        double monthlyPayment = principal *
                ((rateMonthly * Math.pow(1 + rateMonthly, lengthMonths)) /
                (Math.pow(1 + rateMonthly, lengthMonths) - 1));

        //calculate total interest paid: total = (payment * months) - principal
        double interestPaid = (monthlyPayment * lengthMonths) - principal;

        //print calculation result
        System.out.printf("\nMonthly payment: $%.2f\nTotal interest paid: $%.2f", monthlyPayment, interestPaid);
    }
}
