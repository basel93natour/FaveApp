package com.basel.natour.myapplication.model.pagination;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesRequest;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.basel.natour.myapplication.network.MoviesServiceApi;

import io.reactivex.disposables.CompositeDisposable;

public class MoviesDataSourceFactory extends DataSource.Factory<Integer, MoviesModel> {


    CompositeDisposable compositeDisposable;
    MoviesServiceApi moviesServiceApi;
    MoviesRequest moviesRequest;

    public MoviesDataSourceFactory(CompositeDisposable compositeDisposable
            , MoviesServiceApi moviesServiceApi, MoviesRequest moviesRequest) {

        this.compositeDisposable=compositeDisposable;
        this.moviesServiceApi=moviesServiceApi;
        this.moviesRequest=moviesRequest;
    }

    @NonNull
    @Override
    public DataSource<Integer, MoviesModel> create() {
        return new MoviesDataSource( compositeDisposable, moviesServiceApi,moviesRequest);
    }
}
