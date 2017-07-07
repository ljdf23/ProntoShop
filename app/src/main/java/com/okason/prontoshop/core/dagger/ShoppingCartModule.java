package com.okason.prontoshop.core.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.okason.prontoshop.common.ShoppingCart;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Luis on 07/07/2017.
 */

@Module
public class ShoppingCartModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ShoppingCart providesShoppingCart(SharedPreferences preferences) {
        return new ShoppingCart(preferences);
    }

}
