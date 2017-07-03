package com.okason.prontoshop.core.listeners;

import com.okason.prontoshop.model.Customer;

/**
 * Created by Gerardo on 22/06/2017.
 */

public interface OnCustomerSelectedListener {
    void OnSelectCustomer(Customer customer);

    void OnLongClickCustomer(Customer customer);

}
