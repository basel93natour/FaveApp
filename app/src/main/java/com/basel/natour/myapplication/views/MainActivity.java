package com.basel.natour.myapplication.views;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basel.natour.myapplication.R;
import com.basel.natour.myapplication.adapters.MoviesListPagedAdapter;
import com.basel.natour.myapplication.di.ViewModelProviderFactory;
import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    MainActivityViewModel mainActivityViewModel;
    @BindView(R.id.recyclerView_view)
    RecyclerView recyclerViewView;

    MoviesListPagedAdapter moviesListPagedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );

        mainActivityViewModel = ViewModelProviders.of( this , viewModelProviderFactory ).get(
                MainActivityViewModel.class );
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( this );
        recyclerViewView.setLayoutManager( layoutManager );
        moviesListPagedAdapter=new MoviesListPagedAdapter( this );
        recyclerViewView.setAdapter( moviesListPagedAdapter );

        mainActivityViewModel.getMovies();
        mainActivityViewModel.getPagedListMutableLiveData().observe( MainActivity.this ,
                new Observer<PagedList<MoviesModel>>() {
                    @Override
                    public void onChanged(PagedList<MoviesModel> moviesModels) {
                        moviesListPagedAdapter.submitList( moviesModels );
                    }
                } );




    }
}
