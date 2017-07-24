package com.okason.prontoshop.core.listeners;

/**
 * Created by Gerardo on 11/07/2017.
 */

public interface OnDatabaseOperationCompleteListener {

    void onDatabaseOperationFailed(String error);

    void onDatabaseOperationSucceded(String message);
}
