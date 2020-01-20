package com.basel.natour.myapplication.di.Modules;

import com.basel.natour.myapplication.views.MainActivity.MainActivity;
import com.basel.natour.myapplication.views.MovieDetailsActivity.MovieDetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MovieDetailsActivity contributeMovieDetailsActivity();
}
