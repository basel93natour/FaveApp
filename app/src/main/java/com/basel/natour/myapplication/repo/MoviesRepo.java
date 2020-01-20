package com.basel.natour.myapplication.repo;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;

import com.basel.natour.myapplication.database.MoviesDao;
import com.basel.natour.myapplication.model.MovieDetailsModel;
import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesRequest;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.basel.natour.myapplication.model.pagination.MoviesDataSourceFactory;
import com.basel.natour.myapplication.network.MoviesServiceApi;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MoviesRepo {

    MoviesServiceApi moviesServiceApi;
    MoviesDao moviesDao;
    MoviesDataSourceFactory moviesDataSourceFactory;
    CompositeDisposable compositeDisposable;
    MutableLiveData<PagedList<MoviesModel>> moviesLiveData=new MutableLiveData<>(  );
    PagedList.Config pagedListConfig;
    Executor executor;
    @Inject
    public MoviesRepo(MoviesServiceApi moviesServiceApi, MoviesDao moviesDao, CompositeDisposable compositeDisposable, Executor executor) {
        this.moviesDao=moviesDao;
        this.moviesServiceApi=moviesServiceApi;
        this.compositeDisposable=compositeDisposable;
        this.executor=executor;
        pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20)
                        .build();

    }

    public void getMovies(MoviesRequest moviesRequest,int initialPageNumber)
    {


        moviesDataSourceFactory=new MoviesDataSourceFactory( compositeDisposable,moviesServiceApi,moviesRequest,moviesDao,executor,initialPageNumber);


        Observable<PagedList<MoviesModel>> pagedListBuilder=new RxPagedListBuilder<>( moviesDataSourceFactory,pagedListConfig )
                .buildObservable();

        compositeDisposable.add(
                pagedListBuilder.subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .distinctUntilChanged()
                .subscribe(
                        new Consumer<PagedList<MoviesModel>>() {
                            @Override
                            public void accept(final PagedList<MoviesModel> moviesModels) throws Exception {
                                if ( moviesLiveData.getValue() == null )
                                    moviesLiveData.setValue( moviesModels );
                                else
                                    moviesLiveData.getValue().addAll( moviesModels );
                            }
                        } ,
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                            }
                        }
                ));
    }


    public Single<MovieDetailsModel> getMovieDetails(int movie_id)
    {
       return moviesServiceApi.getMovieDetails( movie_id,"328c283cd27bd1877d9080ccb1604c91" )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() );
    }

    public void dispose()
    {
        if (!compositeDisposable.isDisposed() )
            compositeDisposable.dispose();
    }

    public LiveData<PagedList<MoviesModel>> getMoviesLiveData() {
        return moviesLiveData;
    }

}
