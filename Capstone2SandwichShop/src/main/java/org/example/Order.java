package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<String> chips;
    private BigDecimal total;
    private final BigDecimal CHIPS_COST = BigDecimal.valueOf(1.50);


    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();

        total = BigDecimal.valueOf(0);
    }


    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<String> getChips() {
        return chips;
    }

    public void setChips(List<String> chips) {
        this.chips = chips;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCHIPS_COST() {
        return CHIPS_COST;
    }


    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(String chips) {
        this.chips.add(chips);
    }

    public void calculateTotal() {
        for (Sandwich sandwich : sandwiches) {
            total = total.add(sandwich.getBread().getCost());
            for (Topping topping : sandwich.getToppings()) {
                total  = total.add(topping.getCost());
            }
        }

        for (Drink drink : drinks) { total = total.add(drink.getCost()); }

        total = total.add(BigDecimal.valueOf(chips.size() * 1.50));
    }
}
