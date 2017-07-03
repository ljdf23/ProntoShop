package com.okason.prontoshop.core.listeners;

import com.okason.prontoshop.model.Product;

/**
 * Created by Gerardo on 20/06/2017.
 */

public interface OnProductSelectedListener {
    void onSelectedProduct(Product selectedProduct);

    void onLongClickProduct(Product clickedProduct);
}
