package com.okason.prontoshop.ui.customerlist;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okason.prontoshop.R;
import com.okason.prontoshop.core.listeners.OnCustomerSelectedListener;
import com.okason.prontoshop.model.Customer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends Fragment implements OnCustomerSelectedListener {

    @BindView(R.id.customer_list_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    TextView mEmptyTextView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private View mRootView;
    private CustomerListAdapter mAdapter;

    public CustomerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_customer_list, container, false);
        ButterKnife.bind(this, mRootView);

        //Set up the adapter
        List<Customer> tempCustomers = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        mAdapter = new CustomerListAdapter(tempCustomers, getContext(), this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if (tempCustomers.size() < 1) {
            showEmptyTextMessage();
        } else {
            hideEmptyTextMessage();
        }

        return mRootView;
    }

    private void hideEmptyTextMessage() {
        mEmptyTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnSelectCustomer(Customer customer) {

    }

    @Override
    public void OnLongClickCustomer(Customer customer) {

    }
}
