package com.basel.natour.myapplication.di.Component;

import android.app.Application;

import com.basel.natour.myapplication.App;
import com.basel.natour.myapplication.di.Modules.ActivityBuilderModule;
import com.basel.natour.myapplication.di.Modules.AppModule;
import com.basel.natour.myapplication.di.Modules.ViewModelProivderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ActivityBuilderModule.class, AppModule.class, AndroidSupportInjectionModule.class, ViewModelProivderModule.class})
public interface AppComponent extends AndroidInjector<App> {


    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
