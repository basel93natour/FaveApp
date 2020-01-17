package com.basel.natour.myapplication.di.Modules;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.basel.natour.myapplication.Utils.AppUtils;
import com.basel.natour.myapplication.database.AppDataBase;
import com.basel.natour.myapplication.database.MoviesDao;
import com.basel.natour.myapplication.network.MoviesServiceApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    Retrofit provideRetrofitClient()
    {
        return new Retrofit.Builder()
                .baseUrl( "http://api.themoviedb.org/3/" )
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .client( AppUtils.getOkHttpClient().build())
                .build();
    }

    @Provides
    @Singleton
    public MoviesServiceApi provideMoviesServiceApi(Retrofit retrofit)
    {
        return retrofit.create( MoviesServiceApi.class );
    }

    @Provides
    @Singleton
    public AppDataBase provideAppDatabase(Application application)
    {
        return Room.databaseBuilder(application,AppDataBase.class,"movies").build();
    }

    @Provides
    @Singleton
    public MoviesDao provideMoviesDao(AppDataBase appDataBase)
    {
        return appDataBase.moviesDao();
    }

    @Provides
    @Singleton
    public CompositeDisposable provideCompsiteDisposable()
    {
        return new CompositeDisposable(  );
    }
}
