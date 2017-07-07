package com.okason.prontoshop.core.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Luis on 07/07/2017.
 */

@Module
public class BusModule {

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }

}
