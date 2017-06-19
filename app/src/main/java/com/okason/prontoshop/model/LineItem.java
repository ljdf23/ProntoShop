package com.okason.prontoshop.model;

/**
 * Created by Gerardo on 18/06/2017.
 */

public class LineItem extends Product{
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private double getSumPrice() {
        return getSalePrice() * quantity;
    }
}
