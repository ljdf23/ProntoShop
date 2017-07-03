package com.okason.prontoshop.core.listeners;

import com.okason.prontoshop.model.LineItem;

/**
 * Created by Gerardo on 22/06/2017.
 */

public interface CartActionsListener {

    void OnItemDeleted(LineItem item);

    void OnItemQtyChange(LineItem item, int qty);

}
