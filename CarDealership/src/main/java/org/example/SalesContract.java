package org.example;

public class SalesContract extends Contract {
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;


    public SalesContract(String date, String customerName, String customerEmail, String customerPhone, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, customerPhone, vehicleSold);

        this.salesTax = getVehicleSold().getPrice() * 0.05;
        this.recordingFee = 100;
        if (getVehicleSold().getPrice() <= 10000) {
            this.processingFee = 295;
        }
        else {
            this.processingFee = 495;
        }

        this.isFinanced = isFinanced;
    }


    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }


    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + processingFee + recordingFee + salesTax;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0;
        }

        double monthlyInterestRate;
        int months;
        double totalPrice = getTotalPrice();
        if (totalPrice >= 10000) {
            monthlyInterestRate = 0.0425 / 12;
            months = 48;
        }
        else {
            monthlyInterestRate = 0.0525 / 12;
            months = 24;
        }

        return ((totalPrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, months * -1)));
    }
}
