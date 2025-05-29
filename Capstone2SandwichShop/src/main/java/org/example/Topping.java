package org.example;

import java.math.BigDecimal;

public class Topping {
    private String type;
    private String name;
    private BigDecimal cost;
    //base costs
    private static final BigDecimal BREAD_COST = BigDecimal.valueOf(5.50);
    private static final BigDecimal MEAT_COST = BigDecimal.valueOf(1.00);
    private static final BigDecimal CHEESE_COST = BigDecimal.valueOf(0.75);


    public Topping(String type, String name) {
        this.type = type;
        this.name = name;

        //set base cost according to topping type
        switch (type) {
            case "bread":
                this.cost = BREAD_COST;
                break;
            case "meat":
                this.cost = MEAT_COST;
                break;
            case "cheese":
                this.cost = CHEESE_COST;
                break;
            default:
                this.cost = BigDecimal.valueOf(0.00);
                break;
        }
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


    public void addToCost(BigDecimal addCost) {
        cost = cost.add(addCost);
    }
}
