package com.basel.natour.myapplication.di.Modules;

import androidx.lifecycle.ViewModelProvider;

import com.basel.natour.myapplication.di.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelProivderModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}
