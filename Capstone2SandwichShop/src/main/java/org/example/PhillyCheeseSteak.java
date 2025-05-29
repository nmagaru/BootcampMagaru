package org.example;

import java.util.ArrayList;
import java.util.List;

public class PhillyCheeseSteak extends Sandwich{
    public PhillyCheeseSteak() {
        //set philly values by default
        setSize(1);
        setToasted(true);

        Topping bltBread = new Topping("bread", "white");
        setBread(bltBread);

        List<Topping> bltToppings = new ArrayList<>();
        bltToppings.add(new Topping("meat", "steak"));
        bltToppings.add(new Topping("cheese", "american"));
        bltToppings.add(new Topping("other toppings", "peppers"));
        bltToppings.add(new Topping("sauce", "mayo"));
        setToppings(bltToppings);
    }
}
