package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartList;


    public ShoppingCart() {
        this.cartList = new ArrayList<>();
    }


    public void addProductToCart (Product product) {
        cartList.add(product);
    }

    public void removeProductFromCart (String sku) {
        Product productToRemove = null;

        for (int i = 0; i < cartList.size(); i++) {
            if (sku.equalsIgnoreCase(cartList.get(i).getSku())) {
                productToRemove = cartList.get(i);
                break;
            }
        }

        if (productToRemove != null) {
            cartList.remove(productToRemove);
        }
    }

    public double getCartTotal() {
        double total = 0.0;

        for (Product product : cartList) {
            total += product.getPrice();
        }

        return total;
    }

    public void displayCart() {
        ProductRepository.displayProducts(cartList);
    }
}
