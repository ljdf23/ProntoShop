package com.okason.prontoshop.ui.productlist;


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
import com.okason.prontoshop.core.listeners.OnProductSelectedListener;
import com.okason.prontoshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */


//responsable of call the bridge
public class ProductListFragment extends Fragment implements OnProductSelectedListener {
    @BindView(R.id.product_list_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private View mRootView;
    private ProductListAdapter mAdapter;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, mRootView);

        //setup Adapter
        List<Product> tempProducts = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        mAdapter = new ProductListAdapter(tempProducts, getContext(), this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if (tempProducts.size() < 1)
            showEmptyTextMessage();
        else
            hideEmptyTextMessage();
        return mRootView;
    }

    private void hideEmptyTextMessage() {
        mEmptyText.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSelectedProduct(Product selectedProduct) {

    }

    @Override
    public void onLongClickProduct(Product clickedProduct) {

    }
}
