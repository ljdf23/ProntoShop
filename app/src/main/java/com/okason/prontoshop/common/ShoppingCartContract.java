package com.okason.prontoshop.common;

import com.okason.prontoshop.model.Customer;
import com.okason.prontoshop.model.LineItem;

import java.util.List;

/**
 * Created by Gerardo on 04/07/2017.
 */

public interface ShoppingCartContract {
    void addItemToCart(LineItem item);

    void removeItemFromCart(LineItem item);

    void clearAllItemsFromCart();

    List<LineItem> getSgoppingCart();

    void setCustomer(Customer customer);

    void updateItemQty(LineItem item, int qty);

    Customer getSelectedCustomer();

    void completeCheckout();
}
