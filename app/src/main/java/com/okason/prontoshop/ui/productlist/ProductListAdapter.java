package com.okason.prontoshop.ui.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.okason.prontoshop.R;
import com.okason.prontoshop.core.listeners.OnProductSelectedListener;
import com.okason.prontoshop.model.Product;
import com.okason.prontoshop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gerardo on 20/06/2017.
 */

//Adapter is the bridge between the data and the view
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final Context mContext;
    private final OnProductSelectedListener mListener;  //bridge between adapter and fragment (implemented class)
    private List<Product> mProducts;

    public ProductListAdapter(List<Product> mProducts, Context mContext, OnProductSelectedListener mListener) {
        this.mProducts = mProducts;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mProducts != null) {
            Product product = mProducts.get(position);
            Picasso.with(mContext)
                    .load(product.getImagePath())
                    .fit()  //fit into available space
                    .placeholder(R.drawable.default_image)
                    .into(holder.productImage);

            holder.productName.setText(product.getProductName());
            holder.category.setText(product.getCategoryName());
            holder.productPrice.setText(Formatter.formatCurrency(product.getPurchasePrice()));

            String productDescription = product.getDescription();
            String shortDescription = productDescription.substring(0, Math.min(productDescription.length(), 70));
            holder.description.setText(shortDescription);
        }
    }

    @Override
    public int getItemCount() {
        if (mProducts != null) {
            return mProducts.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.text_view_product_name)
        TextView productName;
        @BindView(R.id.textview_product_category)
        TextView category;
        @BindView(R.id.textview_product_description)
        TextView description;
        @BindView(R.id.image_view_add_to_cart_button)
        TextView addToCartButton;
        @BindView(R.id.textview_product_price)
        TextView productPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            addToCartButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Product selectedProduct = mProducts.get(getLayoutPosition());
            mListener.onSelectedProduct(selectedProduct); //we pass the responsability to the fragment (this is the bridge)
        }

        @Override
        public boolean onLongClick(View v) {
            Product clickedProduct = mProducts.get(getLayoutPosition());
            mListener.onLongClickProduct(clickedProduct);  //we pass the responsability to the fragment (this is the bridge)
            return false;
        }
    }

}
