package com.basel.natour.myapplication.views.MainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.basel.natour.myapplication.R;
import com.basel.natour.myapplication.adapters.MoviesListPagedAdapter;
import com.basel.natour.myapplication.di.ViewModelProviderFactory;
import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.views.BaseActivity;
import com.basel.natour.myapplication.views.MovieDetailsActivity.MovieDetailsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MoviesListPagedAdapter.OnMovieClickListener {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    MainActivityViewModel mainActivityViewModel;
    @BindView(R.id.recyclerView_view)
    RecyclerView recyclerViewView;

    MoviesListPagedAdapter moviesListPagedAdapter;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );

        mainActivityViewModel = ViewModelProviders.of( this , viewModelProviderFactory ).get(
                MainActivityViewModel.class );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerViewView.setLayoutManager( layoutManager );
        moviesListPagedAdapter = new MoviesListPagedAdapter( this , this );
        recyclerViewView.setAdapter( moviesListPagedAdapter );


        mainActivityViewModel.getMovies( 1 );

        //observe movie lists coming from api for any changes
        mainActivityViewModel.getPagedListMutableLiveData().observe( MainActivity.this ,
                new Observer<PagedList<MoviesModel>>() {
                    @Override
                    public void onChanged(PagedList<MoviesModel> moviesModels) {

                        moviesListPagedAdapter.submitList( moviesModels );

                    }
                } );


        swiperefresh.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                int lastKey=moviesListPagedAdapter.getCurrentList().getLoadedCount();
                mainActivityViewModel.getMovies( (lastKey/20)+1 );
                swiperefresh.setRefreshing(false); // Disables the refresh icon
            }
        } );
    }

    @Override
    public void doOnClick(MoviesModel moviesModel) {
        Intent intent = new Intent( this , MovieDetailsActivity.class );
        intent.putExtra( "movie_id" , moviesModel.getId() );
        startActivity( intent );
        overridePendingTransition( R.anim.slide_up , R.anim.no_animation );

    }
}
