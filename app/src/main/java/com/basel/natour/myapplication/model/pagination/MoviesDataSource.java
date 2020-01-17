package com.basel.natour.myapplication.model.pagination;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.basel.natour.myapplication.BuildConfig;
import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesRequest;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.basel.natour.myapplication.network.MoviesServiceApi;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MoviesDataSource extends PageKeyedDataSource<Integer, MoviesModel> {

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
    public void loadInitial(@NonNull LoadInitialParams<Integer> params , @NonNull LoadInitialCallback<Integer, MoviesModel> callback) {
        getMoviesByPage( 1,2,callback,null );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params , @NonNull LoadCallback<Integer, MoviesModel> callback) {
        getMoviesByPage( params.key,params.key-1,null,callback );
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params , @NonNull LoadCallback<Integer, MoviesModel> callback) {
        getMoviesByPage( params.key,params.key+1,null,callback );
    }

    public void getMoviesByPage(final int currentPage, final int nextPage, final LoadInitialCallback<Integer,MoviesModel> initialCallback, final LoadCallback<Integer,MoviesModel> loadCallback)
    {

        compositeDisposable.add(
                moviesServiceApi.getMovies( "328c283cd27bd1877d9080ccb1604c91",moviesRequest.getReleaseDate(),moviesRequest.getSortBy(),currentPage ).subscribeOn(
                        Schedulers.io() ).observeOn( AndroidSchedulers.mainThread() ).subscribe(
                        new Consumer<MoviesResponse>() {
                            @Override
                            public void accept(MoviesResponse moviesResponses) throws Exception {
                              if ( initialCallback!=null )
                              {
                                  initialCallback.onResult( moviesResponses.getResults(),null,nextPage );
                              }
                              else if ( loadCallback!=null )
                              {
                                loadCallback.onResult( moviesResponses.getResults(),nextPage );
                              }
                            }
                        } ) );

    }
}
