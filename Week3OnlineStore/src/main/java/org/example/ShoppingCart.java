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
                System.out.println(productToRemove.getName() + " successfully removed from cart.\n");
                break;
            }
        }

        if (productToRemove != null) {
            cartList.remove(productToRemove);
        }
        else {
            System.out.println("Product not found.\n");
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
        if (!cartList.isEmpty()) {
            ProductRepository.displayProducts(cartList);
        }
        else {
            System.out.println("Cart is empty.\n");
        }
    }

    public void clearCart() {
        cartList.clear();
    }
}
