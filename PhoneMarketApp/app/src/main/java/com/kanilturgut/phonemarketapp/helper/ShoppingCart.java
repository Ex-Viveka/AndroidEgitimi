package com.kanilturgut.phonemarketapp.helper;

import com.kanilturgut.phonemarketapp.model.Product;

import java.util.LinkedList;
import java.util.List;

/**
 * Author   : kanilturgut
 * Date     : 23/10/15
 * Time     : 19:11
 */
public class ShoppingCart {

    private static ShoppingCart instance;
    private List<Product> mProducts;

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }

        return instance;
    }

    private ShoppingCart() {
        mProducts = new LinkedList<>();
    }

    public List<Product> getShoppingCart() {
        return this.mProducts;
    }

    public void addProductToCart(Product product) {
        this.mProducts.add(product);
    }

    public void removeProductFromCart(Product product) {
        this.mProducts.remove(product);
    }
}
