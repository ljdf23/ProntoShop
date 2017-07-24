package com.okason.prontoshop.ui.customerlist;

import com.okason.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoshop.model.Customer;

import java.util.List;

/**
 * Created by Gerardo on 11/07/2017.
 */

public interface CustomerListContract {

    public interface View {

        void showCustomer(List<Customer> customers);

        void showAddCustomerForm();

        void showEditCustomerForm(Customer customer);

        void showDeleteCustomerPrompt(Customer customer);

        void showEmptyText();

        void hideEmptyText();

        void showMessage(String message);
    }

    public interface Actions {

        void loadCustomer();

        Customer getCustomer(long id);

        void onCustomerSelected(Customer customer);

        void onAddCustomerButtonClicked();

        void addCustomer(Customer customer);

        void onDeleteCustomerButtonClicked(Customer customer);

        void deleteCustomer(Customer customer);

        void onEditCustomerButtonClicked(Customer customer);

        void updateCustomer(Customer customer);

    }

    public interface Repository {

        List<Customer> getAllCustomers();

        Customer getCustomerById(long id);

        void onDeleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener);

        void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener);

        void updatedCustomer(Customer customer, OnDatabaseOperationCompleteListener listener);
    }
}
