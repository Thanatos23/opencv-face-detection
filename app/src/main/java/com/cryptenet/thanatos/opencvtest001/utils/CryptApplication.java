package com.cryptenet.thanatos.opencvtest001.utils;

import android.app.Application;

import com.cryptenet.thanatos.opencvtest001.modules.AppModule;

public class CryptApplication extends Application {
    AppComponents appComponents;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponents = DaggerAppComponents
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponents getAppComponents() {
        return appComponents;
    }
}
