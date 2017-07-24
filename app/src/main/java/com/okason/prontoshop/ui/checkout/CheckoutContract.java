package com.okason.prontoshop.ui.checkout;

import com.okason.prontoshop.model.LineItem;
import com.okason.prontoshop.model.Transaction;

import java.util.List;

/**
 * Created by Gerardo on 11/07/2017.
 */

public interface CheckoutContract {

    public interface View {
        void showLineItem(List<LineItem> items);

        void showEmptyText();

        void showCartTotals(double tax, double subTotal, double total);

        void showConfirmCheckout();

        void showConfirmClearCart();

        void hideText();

        void showMessage(String message);

    }

    public interface Actions {
        void loadLineItems();

        void onCheckoutButtonClicked();

        void onDeleteItemButtonClicked(LineItem item);

        void checkout();

        void onClearButtonClicked();

        void clearShoppingCart();

        void setPaymentType(String paymentType);

        void markAsPaid(boolean paid);

        void onItemQuantityChanged(LineItem item, int qty);

    }

    public interface Repository {
        List<LineItem> getAllLineItems();

        void saveTransaction(Transaction transaction);

        void updateTransaction(Transaction transaction);
    }

}
