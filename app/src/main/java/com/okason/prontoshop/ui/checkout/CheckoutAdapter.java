package com.okason.prontoshop.ui.checkout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.okason.prontoshop.R;
import com.okason.prontoshop.core.listeners.CartActionsListener;
import com.okason.prontoshop.model.LineItem;
import com.okason.prontoshop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gerardo on 22/06/2017.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    private final Activity mContext;
    private final CartActionsListener mListener;
    private List<LineItem> mLineItems;

    public CheckoutAdapter(List<LineItem> mLineItems, Context mContext, CheckoutFragment mListener) {
        this.mLineItems = mLineItems;
        this.mContext = (Activity) mContext;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shopping_cart_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LineItem item = mLineItems.get(position);
        Picasso.with(mContext)
                .load(item.getImagePath())
                .fit()
                .placeholder(R.drawable.default_image)
                .into(holder.productImage);

        holder.productName.setText(item.getProductName());
        holder.producPrice.setText(Formatter.formatCurrency(item.getSalePrice()));
        holder.qtyEditText.setText(String.valueOf(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        if (mLineItems != null) {
            return mLineItems.size();
        } else {
            return 0;
        }
    }

    //the adapter dont have logic for what to do with quantity
    //only retrieve the information and pass it on
    //another entity make the desition for what to do
    private void updateQtyDialog(final LineItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = mContext.getLayoutInflater();

        View rootView = inflater.inflate(R.layout.dialog_enter_item_qty, null);
        dialog.setView(rootView);

        View titleView = inflater.inflate(R.layout.dialog_title, null);
        TextView titleText = (TextView) titleView.findViewById(R.id.text_view_dialog_title);
        titleText.setText(item.getProductName());
        dialog.setCustomTitle(titleView);

        final EditText qtyEditText = (EditText) rootView.findViewById(R.id.edit_text_item_qty);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!qtyEditText.getText().toString().isEmpty()) {
                    int qtyEntered = Integer.parseInt(qtyEditText.getText().toString());
                    mListener.OnItemQtyChange(item, qtyEntered);
                } else
                    Toast.makeText(mContext, "Invalid quantity", Toast.LENGTH_SHORT).show();
            }
        });


        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        @BindView(R.id.product_image)
        ImageView productImage;  //butterknife is view injection framework development
        @BindView(R.id.text_view_product_name)
        TextView productName;
        @BindView(R.id.text_view_price)
        TextView producPrice;
        @BindView(R.id.edit_text_qty)
        EditText qtyEditText;
        @BindView(R.id.button_delete)
        Button deletedButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this.itemView);
            deletedButton.setOnClickListener(this);
            qtyEditText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    LineItem item = mLineItems.get(getLayoutPosition());
                    updateQtyDialog(item);
                }
            });
        }

        @Override
        public void onClick(View v) {
            LineItem item = mLineItems.get(getLayoutPosition());
            mListener.OnItemDeleted(item); //another entity make the delete, this adapter only display
        }
    }
}
