package com.basel.natour.myapplication.model.pagination;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.basel.natour.myapplication.database.MoviesDao;
import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesRequest;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.basel.natour.myapplication.network.MoviesServiceApi;

import java.util.concurrent.Executor;

import io.reactivex.disposables.CompositeDisposable;

public class MoviesDataSourceFactory extends DataSource.Factory<Integer, MoviesModel> {


    CompositeDisposable compositeDisposable;
    MoviesServiceApi moviesServiceApi;
    MoviesRequest moviesRequest;
    MoviesDao moviesDao;
    Executor executor;
    int initialPageNumber;
    public MoviesDataSourceFactory(CompositeDisposable compositeDisposable
            , MoviesServiceApi moviesServiceApi, MoviesRequest moviesRequest
            ,MoviesDao moviesDao,Executor executor
    ,int initialPageNumber) {

        this.compositeDisposable=compositeDisposable;
        this.moviesServiceApi=moviesServiceApi;
        this.moviesRequest=moviesRequest;
        this.moviesDao=moviesDao;
        this.executor=executor;
        this.initialPageNumber=initialPageNumber;
    }

    @NonNull
    @Override
    public DataSource<Integer, MoviesModel> create() {
        return new MoviesDataSource( compositeDisposable, moviesServiceApi,moviesRequest,moviesDao,executor,initialPageNumber);
    }
}
