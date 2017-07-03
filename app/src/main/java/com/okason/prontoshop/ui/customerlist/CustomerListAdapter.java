package com.okason.prontoshop.ui.customerlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.okason.prontoshop.R;
import com.okason.prontoshop.core.listeners.OnCustomerSelectedListener;
import com.okason.prontoshop.model.Customer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gerardo on 21/06/2017.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private final Context mContext;
    private final OnCustomerSelectedListener mListener;
    private List<Customer> mCustomers;
    private boolean shouldHighlightSelectedCustomer = false;
    private int selectedPosition = 0;

    public CustomerListAdapter(List<Customer> mCustomers, Context mContext, OnCustomerSelectedListener mListener) {
        this.mCustomers = mCustomers;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer_list, parent, false);
        //TODO check false, he said that we do not want this inflated rith now , so we have to holding to it until we need it
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Customer selectedCustomer = mCustomers.get(position);
        holder.customerName.setText(selectedCustomer.getCustomerName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddress());
        Picasso.with(mContext)
                .load(selectedCustomer.getProfileImagePath())
                .fit()
                .placeholder(R.drawable.profile_icon)
                .into(holder.customerHeadShot);

        if (shouldHighlightSelectedCustomer) {
            if (selectedPosition == position) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.primary_light));
                //TODO ContexCompat
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                //Color has this static method, instead above, that we want specific color, and use ContextCompat//
            }
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        if (mCustomers != null) {
            return mCustomers.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.image_view_customer_head_shot)
        ImageView customerHeadShot;
        @BindView(R.id.text_view_customer_email)
        TextView customerEmail;
        @BindView(R.id.text_view_customer_name)
        TextView customerName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            //TODO Check the differences between this and productlistadapter
            //check the longclick wherever u want
        }

        @Override
        public void onClick(View v) {
            shouldHighlightSelectedCustomer = true;
            selectedPosition = getLayoutPosition();
            Customer customer = mCustomers.get(selectedPosition);
            mListener.OnSelectCustomer(customer); //The bridge between adapter and fragment (a differente class)
            notifyDataSetChanged();
        }

        @Override
        public boolean onLongClick(View v) {
            Customer customer = mCustomers.get(selectedPosition);
            mListener.OnLongClickCustomer(customer); //The bridge between adapter and fragment (a differente class)
            return true;
        }
    }
}
