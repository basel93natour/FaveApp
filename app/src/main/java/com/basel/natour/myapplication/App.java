package com.basel.natour.myapplication;

import com.basel.natour.myapplication.di.Component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application( this ).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
