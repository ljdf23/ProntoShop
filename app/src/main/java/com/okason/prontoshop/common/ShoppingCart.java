package com.okason.prontoshop.common;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.okason.prontoshop.model.Customer;
import com.okason.prontoshop.model.LineItem;
import com.okason.prontoshop.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerardo on 18/06/2017.
 */

public class ShoppingCart implements ShoppingCartContract {

    private final static String LOG_TAG = ShoppingCart.class.getSimpleName();
    private static boolean DEBUG = false;
    private final SharedPreferences mSharedPreferences;
    private List<LineItem> shoppingCart;
    private Customer selectedCustomer;
    private SharedPreferences.Editor editor;

    public ShoppingCart(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
        initShoppingCart();
    }

    private void initShoppingCart() {
        shoppingCart = new ArrayList<>();
        selectedCustomer = new Customer();
        Gson gson = new Gson();

        //check if there are items saved to the shared preference
        if (mSharedPreferences.getBoolean(Constants.OPEN_CART_EXISTS, false)) {
            String serializedCartItems = mSharedPreferences.getString(Constants.SERIALIZED_CART_ITEMS, "");
            String serializedCustomers = mSharedPreferences.getString(Constants.SERIALIZED_CART_CUSTOMER, "");
            if (DEBUG) {
                Log.d(LOG_TAG, serializedCartItems);
                Log.d(LOG_TAG, serializedCustomers);
            }

            if (!serializedCartItems.equals("")) {
                shoppingCart = gson.<ArrayList<LineItem>>fromJson(serializedCartItems,
                        new TypeToken<ArrayList<LineItem>>() {
                        }.getType());
            }

            if (!serializedCustomers.equals("")) {
                selectedCustomer = gson.fromJson(serializedCustomers, Customer.class);
            }
        }

    }

    public void saveCartToPreference() {
        if (shoppingCart != null) {
            Gson gson = new Gson();
            String serializedItems = gson.toJson(shoppingCart);
            String serializedCustomer = gson.toJson(selectedCustomer);

            if (DEBUG) {
                Log.d(LOG_TAG, "Saving serialized cart items: " + serializedItems);
                Log.d(LOG_TAG, "Saving serialized customer: " + serializedCustomer);
            }

            this.editor.putString(Constants.SERIALIZED_CART_ITEMS, serializedItems).commit();
            this.editor.putString(Constants.SERIALIZED_CART_CUSTOMER, serializedCustomer).commit();
            editor.putBoolean(Constants.OPEN_CART_EXISTS, true).commit();
        }
    }

    @Override
    public void addItemToCart(LineItem item) {
        boolean isItemInCart = false;
        int itemPosition = 0;

        for (LineItem tempItem : shoppingCart) {
            if (tempItem.getId() == item.getId()) {
                itemPosition = shoppingCart.indexOf(tempItem);
                isItemInCart = true;
                LineItem selectedItem = shoppingCart.get(itemPosition);
                selectedItem.setQuantity(tempItem.getQuantity() + item.getQuantity());
                shoppingCart.set(itemPosition, selectedItem);
                break;
            }
        }

        if (!isItemInCart) {
            shoppingCart.add(item);
        }
    }

    @Override
    public void removeItemFromCart(LineItem item) {
        shoppingCart.remove(item);
    }

    @Override
    public void clearAllItemsFromCart() {
        shoppingCart.clear();
    }

    @Override
    public List<LineItem> getSgoppingCart() {
        return shoppingCart;
    }

    @Override
    public void setCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    @Override
    public void updateItemQty(LineItem item, int qty) {
        boolean isItemAlreadyInCart = false;
        int itemPosition = 0;

        for (LineItem tempItem : shoppingCart) {
            if (tempItem.getId() == item.getId()) {
                itemPosition = shoppingCart.indexOf(tempItem);
                isItemAlreadyInCart = true;
                LineItem selectedItem = shoppingCart.get(itemPosition);
                selectedItem.setQuantity(tempItem.getQuantity() + item.getQuantity());
                shoppingCart.set(itemPosition, selectedItem);
                break;
            }
        }

        if (!isItemAlreadyInCart) {
            item.setQuantity(qty);
            shoppingCart.add(item);
        }
    }

    @Override
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void completeCheckout() {
        shoppingCart.clear();
    }
}
