package com.okason.prontoshop.core.dagger;

import android.content.Context;

import com.okason.prontoshop.core.ProntoShopApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gerardo on 18/06/2017.
 */


/*
*
* Used on classes which contains methods annotated with @Provides.
*
*/
@Module
public class AppModule {

    private final ProntoShopApplication app;

    public AppModule(ProntoShopApplication app) {
        this.app = app;
    }

    /*
    Can be used on methods in classes annotated with @Module and is used for methods which provides
    objects for dependencies injection.
    * */
    @Provides

    /*Single instance of this provided object is created and shared.*/
    @Singleton
    public ProntoShopApplication provideApp() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }
}
