package com.okason.prontoshop.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.okason.prontoshop.ui.checkout.CheckoutFragment;
import com.okason.prontoshop.ui.customerlist.CustomerListFragment;
import com.okason.prontoshop.ui.productlist.ProductListFragment;

/**
 * Created by Gerardo on 02/07/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment selectedFragment;

        switch (position) {
            case 1:
                selectedFragment = new CustomerListFragment();
                break;
            case 2:
                selectedFragment = new CheckoutFragment();
                break;
            default:
                selectedFragment = new ProductListFragment();
        }
        return selectedFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Products";
                break;
            case 1:
                title = "Customer";
                break;
            case 2:
                title = "Shopping Cart";
                break;

        }
        return title;
    }


}
