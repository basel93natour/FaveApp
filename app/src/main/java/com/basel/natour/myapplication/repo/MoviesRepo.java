package com.basel.natour.myapplication.repo;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;

import com.basel.natour.myapplication.database.MoviesDao;
import com.basel.natour.myapplication.model.MoviesRequest;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.basel.natour.myapplication.model.Resource;
import com.basel.natour.myapplication.model.Status;
import com.basel.natour.myapplication.model.pagination.MoviesDataSourceFactory;
import com.basel.natour.myapplication.network.MoviesServiceApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    MutableLiveData<PagedList<MoviesResponse>> moviesLiveData=new MutableLiveData<>(  );

    @Inject
    public MoviesRepo(MoviesServiceApi moviesServiceApi, MoviesDao moviesDao, CompositeDisposable compositeDisposable) {
        this.moviesDao=moviesDao;
        this.moviesServiceApi=moviesServiceApi;
        this.compositeDisposable=compositeDisposable;

    }

    public void getMovies(MoviesRequest moviesRequest)
    {
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
//                        .setInitialLoadSizeHint(10)
//                        .setPageSize(20)
                        .build();

        moviesDataSourceFactory=new MoviesDataSourceFactory( compositeDisposable,moviesServiceApi,moviesRequest);

        Observable<PagedList<MoviesResponse>> pagedListBuilder=new RxPagedListBuilder<>( moviesDataSourceFactory,pagedListConfig )
                .buildObservable();

        compositeDisposable.add(
                pagedListBuilder.subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .distinctUntilChanged()
                .doOnSubscribe( new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                } ).subscribe( new Consumer<PagedList<MoviesResponse>>() {
                    @Override
                    public void accept(PagedList<MoviesResponse> moviesResponses) throws Exception {
                       if ( moviesLiveData.getValue()==null )
                           moviesLiveData.setValue( moviesResponses );
                        else
                           moviesLiveData.getValue().addAll( moviesResponses );
                    }
                } , new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                    }
                } ));
    }

    public LiveData<PagedList<MoviesResponse>> getMoviesLiveData() {
        return moviesLiveData;
    }

}
