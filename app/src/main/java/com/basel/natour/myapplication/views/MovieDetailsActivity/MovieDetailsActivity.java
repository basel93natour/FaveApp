package com.basel.natour.myapplication.views.MovieDetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.basel.natour.myapplication.R;
import com.basel.natour.myapplication.di.ViewModelProviderFactory;
import com.basel.natour.myapplication.model.MovieDetailsModel;
import com.basel.natour.myapplication.model.Resource;
import com.basel.natour.myapplication.views.BaseActivity;
import com.basel.natour.myapplication.views.BookMovieActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends BaseActivity {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    MovieDetailsViewModel movieDetailsViewModel;
    @BindView(R.id.movie_overview)
    TextView movieOverview;
    @BindView(R.id.movie_genre)
    TextView movieGenre;
    @BindView(R.id.movie_language)
    TextView movieLanguage;
    @BindView(R.id.movie_duration)
    TextView movieDuration;
    @BindView(R.id.book_movie)
    Button bookMovie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView( R.layout.movie_details_activity );
        ButterKnife.bind( this );
        super.onCreate( savedInstanceState );
        movieDetailsViewModel = ViewModelProviders.of( this , viewModelProviderFactory ).get(
                MovieDetailsViewModel.class );

        int movie_id = getIntent().getIntExtra( "movie_id" , 0 );
        movieDetailsViewModel.getMovieDetails( movie_id );

        movieDetailsViewModel.getMovieDetailsLiveData().observe( this ,
                new Observer<Resource<MovieDetailsModel>>() {
                    @Override
                    public void onChanged(Resource<MovieDetailsModel> movieDetailsModelResource) {
                       if ( movieDetailsModelResource.getStatus().isSuccess() )
                       {
                           movieOverview.setText( movieDetailsModelResource.getData().getOverview().length()!=0? movieDetailsModelResource.getData().getOverview() : "Not Available");
                           movieGenre.setText( movieDetailsViewModel.getMovieGenres(
                                   movieDetailsModelResource.getData().getGenres() ) );
                           movieLanguage.setText( movieDetailsViewModel.getMovieSpokenLanguage(
                                   movieDetailsModelResource.getData().getSpokenLanguages() ) );
                           movieDuration.setText( (movieDetailsModelResource.getData().getRuntime()!=null? (movieDetailsModelResource.getData().getRuntime() + "Min") : "Not Available")   );
                       }
                        else if ( movieDetailsModelResource.getStatus().isFailed() )
                       {
                           Toast.makeText( MovieDetailsActivity.this, movieDetailsModelResource.getErrorMessage(),Toast.LENGTH_LONG).show();
                       }
                    }
                } );
        bookMovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( MovieDetailsActivity.this, BookMovieActivity.class );
                startActivity( intent );
            }
        } );
    }

    @Override
    protected void onDestroy() {
        overridePendingTransition( R.anim.no_animation , R.anim.slide_up );
        super.onDestroy();

    }
}
