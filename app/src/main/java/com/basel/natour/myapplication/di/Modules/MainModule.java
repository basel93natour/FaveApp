package com.basel.natour.myapplication.di.Modules;

import androidx.lifecycle.ViewModel;

import com.basel.natour.myapplication.MainActivityViewModel;
import com.basel.natour.myapplication.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey( MainActivityViewModel.class )
    public  abstract ViewModel bindMainActivityViewModel(MainActivityViewModel mainActivityViewModel);
}
