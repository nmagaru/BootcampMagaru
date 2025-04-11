package org.example;

import java.util.Scanner;

public class PresentValueCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your monthly payout amount: ");
        double payout = scanner.nextDouble(); //PMT

        System.out.print("Enter your expected interest rate as a percentage: ");
        double rateAnnual = scanner.nextDouble() / 100;

        System.out.print("Enter your compounding length in years: ");
        int lengthYears = scanner.nextInt(); //t

        //calculate monthly interest rate: monthly = annual / 12
        double rateMonthly = rateAnnual / 12 ; //r

        //calculate number of monthly payments: number = length * 12
        int lengthMonths = lengthYears * 12; //n

        //calculate present value: PV = PMT × [(1 - (1 + r)^(-n)) / r]
        double presentValue = payout *
                (1 - Math.pow(1 + rateMonthly, lengthMonths * -1)) / rateMonthly;

        //print calculation result
        System.out.printf("\nPresent value: $%.2f", presentValue);
    }
}
