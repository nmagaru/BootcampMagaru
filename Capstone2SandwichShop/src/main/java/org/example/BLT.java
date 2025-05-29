package org.example;

import java.util.ArrayList;
import java.util.List;

public class BLT extends Sandwich{
    public BLT() {
        //set blt values by default
        setSize(1);
        setToasted(true);

        Topping bltBread = new Topping("bread", "white");
        setBread(bltBread);

        List<Topping> bltToppings = new ArrayList<>();
        bltToppings.add(new Topping("meat", "bacon"));
        bltToppings.add(new Topping("cheese", "cheddar"));
        bltToppings.add(new Topping("other toppings", "lettuce"));
        bltToppings.add(new Topping("other toppings", "tomato"));
        bltToppings.add(new Topping("sauce", "ranch"));
        setToppings(bltToppings);
    }
}
