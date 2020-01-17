package com.basel.natour.myapplication.model.pagination;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.basel.natour.myapplication.model.MoviesRequest;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.basel.natour.myapplication.network.MoviesServiceApi;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MoviesDataSource extends PageKeyedDataSource<Integer, MoviesResponse> {

    CompositeDisposable compositeDisposable;
    MoviesServiceApi moviesServiceApi;
    MoviesRequest moviesRequest;

    public MoviesDataSource(CompositeDisposable compositeDisposable
            , MoviesServiceApi moviesServiceApi, MoviesRequest moviesRequest) {

        this.compositeDisposable=compositeDisposable;
        this.moviesServiceApi=moviesServiceApi;
        this.moviesRequest=moviesRequest;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params , @NonNull LoadInitialCallback<Integer, MoviesResponse> callback) {
        getMoviesByPage( 1,2,callback,null );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params , @NonNull LoadCallback<Integer, MoviesResponse> callback) {
        getMoviesByPage( params.key,params.key-1,null,callback );
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params , @NonNull LoadCallback<Integer, MoviesResponse> callback) {
        getMoviesByPage( params.key,params.key+1,null,callback );
    }

    public void getMoviesByPage(final int currentPage, final int nextPage, final LoadInitialCallback<Integer,MoviesResponse> initialCallback, final LoadCallback<Integer,MoviesResponse> loadCallback)
    {

        compositeDisposable.add(
                moviesServiceApi.getMovies( moviesRequest.getReleaseDate(),moviesRequest.getSortBy(),currentPage ).subscribeOn(
                        Schedulers.io() ).observeOn( AndroidSchedulers.mainThread() ).subscribe(
                        new Consumer<List<MoviesResponse>>() {
                            @Override
                            public void accept(List<MoviesResponse> moviesResponses) throws Exception {
                              if ( initialCallback!=null )
                              {
                                  initialCallback.onResult( moviesResponses,null,nextPage );
                              }
                              else if ( loadCallback!=null )
                              {
                                loadCallback.onResult( moviesResponses,nextPage );
                              }
                            }
                        } ) );

    }
}
