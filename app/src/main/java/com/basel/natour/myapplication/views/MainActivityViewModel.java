package com.basel.natour.myapplication.views;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesRequest;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.basel.natour.myapplication.model.pagination.MoviesDataSourceFactory;
import com.basel.natour.myapplication.repo.MoviesRepo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {


    private MoviesRepo moviesRepo;
    private Observer<PagedList<MoviesModel>> observer;
    private MutableLiveData<PagedList<MoviesModel>> pagedListMutableLiveData=new MutableLiveData<>(  );

    @Inject
    public MainActivityViewModel(MoviesRepo moviesRepo) {
        this.moviesRepo=moviesRepo;
        intiObserver();
    }

    private void intiObserver() {
        observer=new Observer<PagedList<MoviesModel>>() {
            @Override
            public void onChanged(PagedList<MoviesModel> moviesResponses) {
                pagedListMutableLiveData.setValue( moviesResponses );
            }
        };
        moviesRepo.getMoviesLiveData().observeForever( observer );
    }


    public void getMovies()
    {
        MoviesRequest moviesRequest =new MoviesRequest();
        moviesRequest.setPage( 1 );
        moviesRequest.setSortBy( "release_date.desc" );
        moviesRequest.setReleaseDate( "2016-12-31" );
        moviesRepo.getMovies( moviesRequest );
    }

    public MutableLiveData<PagedList<MoviesModel>> getPagedListMutableLiveData() {
        return pagedListMutableLiveData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();

    }
}

