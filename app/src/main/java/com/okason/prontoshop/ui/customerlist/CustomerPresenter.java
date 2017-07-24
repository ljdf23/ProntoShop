package com.okason.prontoshop.ui.customerlist;

import com.okason.prontoshop.common.ShoppingCart;
import com.okason.prontoshop.core.ProntoShopApplication;
import com.okason.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoshop.model.Customer;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Gerardo on 12/07/2017.
 */

public class CustomerPresenter implements CustomerListContract.Actions, OnDatabaseOperationCompleteListener {

    private final CustomerListContract.View mView;

    @Inject
    CustomerListContract.Repository mRepository;

    @Inject
    ShoppingCart mCart;

    @Inject
    Bus mBus;

    public CustomerPresenter(CustomerListContract.View mView) {
        this.mView = mView;
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void onDatabaseOperationFailed(String error) {
        mView.showMessage("ERror: " + error);
    }

    @Override
    public void onDatabaseOperationSucceded(String message) {
        mView.showMessage(message);
    }

    @Override
    public void loadCustomer() {
        List<Customer> availableCustomer = mRepository.getAllCustomers();

        if (availableCustomer != null && availableCustomer.size() > 0) {
            mView.hideEmptyText();
            mView.showCustomer(availableCustomer);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public Customer getCustomer(long id) {
        return mRepository.getCustomerById(id);
    }

    @Override
    public void onCustomerSelected(Customer customer) {
        mCart.setCustomer(customer);
    }

    @Override
    public void onAddCustomerButtonClicked() {
        mView.showAddCustomerForm();
    }

    @Override
    public void addCustomer(Customer customer) {
        mRepository.addCustomer(customer, this);
    }

    @Override
    public void onDeleteCustomerButtonClicked(Customer customer) {
        mView.showDeleteCustomerPrompt(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        mRepository.onDeleteCustomer(customer, this);
    }

    @Override
    public void onEditCustomerButtonClicked(Customer customer) {
        mView.showEditCustomerForm(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        mRepository.updatedCustomer(customer, this);
    }


}
