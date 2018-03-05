package com.cryptenet.thanatos.opencvtest001.utils;

import com.cryptenet.thanatos.opencvtest001.MainActivity;
import com.cryptenet.thanatos.opencvtest001.modules.AppModule;

import dagger.Component;

@Component (
    modules = {
        AppModule.class
    }
)
public interface AppComponents {
    void inject(MainActivity mainActivity);
}
