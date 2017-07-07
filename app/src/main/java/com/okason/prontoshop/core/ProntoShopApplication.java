package com.okason.prontoshop.core;

import android.app.Application;

import com.okason.prontoshop.core.dagger.AppComponent;
import com.okason.prontoshop.core.dagger.AppModule;
import com.okason.prontoshop.core.dagger.DaggerAppComponent;

/**
 * Created by Luis on 07/07/2017.
 */

public class ProntoShopApplication extends Application {

    private static AppComponent appComponent;
    private static ProntoShopApplication instance = new ProntoShopApplication();


    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    private void getAppComponent() {
        if (appComponent == null)
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
    }
}
