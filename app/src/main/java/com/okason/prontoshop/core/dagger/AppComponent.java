package com.okason.prontoshop.core.dagger;

import com.okason.prontoshop.common.MainActivity;
import com.okason.prontoshop.common.ShoppingCart;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Gerardo on 18/06/2017.
 */

@Singleton

/*
Used on an interface. This interface is used by Dagger 2
 to generate code which uses the modules to fulfill the
 requested dependencies.
 */

@Component(
        modules = {
                AppModule.class,
                ShoppingCartModule.class,
                BusModule.class
        }
)
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(ShoppingCart cart);

}
