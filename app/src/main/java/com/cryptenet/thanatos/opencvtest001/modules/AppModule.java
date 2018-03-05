package com.cryptenet.thanatos.opencvtest001.modules;

import android.content.Context;

import com.cryptenet.thanatos.opencvtest001.utils.CryptApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private CryptApplication application;

    public AppModule(CryptApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return application;
    }
}
