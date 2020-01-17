package com.basel.natour.myapplication.views;

import androidx.lifecycle.ViewModel;

import com.basel.natour.myapplication.model.pagination.MoviesDataSourceFactory;
import com.basel.natour.myapplication.repo.MoviesRepo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {


    MoviesRepo moviesRepo;

    @Inject
    public MainActivityViewModel(MoviesRepo moviesRepo) {
        this.moviesRepo=moviesRepo;
    }




}

