package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private Topping bread;
    private int size;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich() {
        this.bread = null;
        this.size = 0;
        this.toasted = false;

        this.toppings = new ArrayList<>();
    }

    public Sandwich(Topping bread, int size, boolean toasted) {
        this.bread = bread;
        this.size = size;
        this.toasted = toasted;

        this.toppings = new ArrayList<>();
    }


    public Topping getBread() {
        return bread;
    }

    public void setBread(Topping bread) {
        this.bread = bread;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }


    public boolean toppingTypeInList(String type) {
        return toppings.stream()
                .anyMatch(topping -> type.equalsIgnoreCase(topping.getType()));
    }

    public void addTopping(Topping topping) {
        String type = topping.getType();
        BigDecimal size = BigDecimal.valueOf(this.size);
        BigDecimal addCost;

        //check what kind of topping, add to base price according to size
        switch (type) {
            case "meat":
                if (!toppingTypeInList(type)) {
                    addCost = size.multiply(BigDecimal.valueOf(1.00));
                }
                else {
                    topping.setCost(BigDecimal.valueOf(0.50));
                    addCost = size.multiply(BigDecimal.valueOf(0.50));
                }
                break;
            case "cheese":
                if (!toppingTypeInList(type)) {
                    addCost = size.multiply(BigDecimal.valueOf(0.75));
                }
                else {
                    topping.setCost(BigDecimal.valueOf(0.30));
                    addCost = size.multiply(BigDecimal.valueOf(0.30));
                }
                break;
            default:
                addCost = BigDecimal.valueOf(0.00);
                break;
        }

        topping.addToCost(addCost);
        toppings.add(topping);
    }

    public void clearToppings() {
        toppings.clear();
    }
}
