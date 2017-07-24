package com.okason.prontoshop.ui.productlist;

import com.okason.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoshop.model.Category;
import com.okason.prontoshop.model.Product;

import java.util.List;

/**
 * Created by Gerardo on 11/07/2017.
 */

public class ProductListContract {

    public interface View {

        void showProduct(List<Product> products);

        void showAddProductForm();

        void showEditProductForm(Product product);

        void showDeleteProductPrompt(Product product);

        void showGoogleSearch(Product product);

        void showEmptyText();

        void hideEmptyText();

        void showMessage(String message);
    }

    public interface Actions {

        void loadProducts();

        void onAddProductButtonClicked();

        void onAddToCartButtonClicked(Product product);

        Product getProduct(long id);

        void addProduct(Product product);

        void onDeleteProductButtonClicked(Product product);

        void deleteProduct(Product product);

        void onEditProductButtonClicked(Product product);

        void updateProduct(Product product);

        void onGoogleSearchButtonClicked(Product product);
    }

    //
    public interface Repository {

        List<Product> getAllProducts();

        Product getProductById(long id);

        void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener);

        void addProduct(Product product, OnDatabaseOperationCompleteListener listener);

        void updateProduct(Product product, OnDatabaseOperationCompleteListener listener);

        List<Category> getAllCategories();
    }
}
