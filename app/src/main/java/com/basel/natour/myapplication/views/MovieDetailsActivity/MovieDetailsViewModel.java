package com.basel.natour.myapplication.views.MovieDetailsActivity;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.basel.natour.myapplication.model.Genre;
import com.basel.natour.myapplication.model.MovieDetailsModel;
import com.basel.natour.myapplication.model.SpokenLanguage;
import com.basel.natour.myapplication.repo.MoviesRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class MovieDetailsViewModel extends ViewModel {

    MoviesRepo moviesRepo;
    private MutableLiveData<MovieDetailsModel> mutableLiveData=new MutableLiveData<>(  );
    @Inject
    public MovieDetailsViewModel(MoviesRepo moviesRepo) {
        this.moviesRepo=moviesRepo;
    }

    public void getMovieDetails(int movie_id)
    {
        moviesRepo.getMovieDetails( movie_id ).subscribe( new Consumer<MovieDetailsModel>() {
            @Override
            public void accept(MovieDetailsModel movieDetailsModel) throws Exception {
                if ( movieDetailsModel!=null )
                    mutableLiveData.setValue( movieDetailsModel );
            }
        } );
    }

    public String getMovieGenres(@Nullable List<Genre> genres)
    {
        StringBuilder genresText= new StringBuilder();
        if ( genres!=null )
        {
            for (int i=0;i<genres.size();i++)
            {
                if ( i==genres.size()-1 )
                    genresText.append( genres.get( i ).getName() ).append( "." );
                else
                    genresText.append( genres.get( i ).getName() ).append( "," );
            }
        }

        return genresText.toString();
    }

    public String getMovieSpokenLanguage(@Nullable List<SpokenLanguage> spokenLanguages)
    {
        StringBuilder languageText= new StringBuilder();

        if ( spokenLanguages!=null )
        {
            for (int i=0;i<spokenLanguages.size();i++)
            {
                if ( i==spokenLanguages.size() -1)
                    languageText.append( spokenLanguages.get( i ).getName() ).append( "." );
                else
                    languageText.append( spokenLanguages.get( i ).getName() ).append( "," );
            }
        }

        return languageText.toString();
    }

    public MutableLiveData<MovieDetailsModel> getMovieDetailsLiveData()
    {
        return mutableLiveData;
    }


}
