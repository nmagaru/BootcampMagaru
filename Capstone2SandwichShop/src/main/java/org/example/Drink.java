package org.example;

import java.math.BigDecimal;

public class Drink {
    private int size;
    private String flavor;
    private BigDecimal cost;
    //base cost
    private final BigDecimal DRINK_COST = BigDecimal.valueOf(2.00);


    public Drink(int size, String flavor) {
        this.size = size;
        this.flavor = flavor;

        //set cost according to size
        BigDecimal sizeBD = BigDecimal.valueOf(size);
        this.cost = DRINK_COST.add(sizeBD.multiply(BigDecimal.valueOf(0.5)));
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
