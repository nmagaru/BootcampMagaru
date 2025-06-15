package org.example;

public class LeaseContract extends Contract{
    private double expEndingPrice;
    private double leaseFee;


    public LeaseContract(String date, String customerName, String customerEmail, String customerPhone, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, customerPhone, vehicleSold);

        this.expEndingPrice = getVehicleSold().getPrice() * 0.5;
        this.leaseFee = getVehicleSold().getPrice() * 0.07;
    }


    public double getExpEndingPrice() {
        return expEndingPrice;
    }

    public void setExpEndingPrice(double expEndingPrice) {
        this.expEndingPrice = expEndingPrice;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }


    @Override
    public double getTotalPrice() {
        return expEndingPrice + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = 0.04 / 12;
        int months = 36;
        double totalPrice = getTotalPrice();

        return ((totalPrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, months * -1)));
    }
}
