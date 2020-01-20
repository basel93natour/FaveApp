package com.basel.natour.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.basel.natour.myapplication.R;
import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MoviesListPagedAdapter extends PagedListAdapter<MoviesModel,MoviesListPagedAdapter.ViewHolder> {

    Context context;
    OnMovieClickListener movieClickListener;
    public MoviesListPagedAdapter(Context context,OnMovieClickListener movieClickListener) {
        super( new MovieDiffUtil() );
        this.context=context;
        this.movieClickListener=movieClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.movie_list_item,parent,false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder , int position) {
        final MoviesModel moviesModel=getItem( position );
        holder.movie_title.setText( moviesModel.getTitle() );
        holder.movie_popularity.setText( ""+moviesModel.getPopularity() );
        //http://image.tmdb.org/t/p/original/ posters base url
        if ( moviesModel.getPosterPath()!=null )
            Glide.with( context ).load("http://image.tmdb.org/t/p/original/"+ moviesModel.getPosterPath() ).into( holder.movie_poster );


        holder.card_movie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieClickListener.doOnClick( moviesModel );
            }
        } );
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView movie_title;
        TextView movie_popularity;
        ImageView movie_poster;
        CardView card_movie;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            movie_title=itemView.findViewById( R.id.movie_title );
            movie_popularity=itemView.findViewById( R.id.movie_popularity );
            movie_poster=itemView.findViewById( R.id.movie_poster );
            card_movie=itemView.findViewById( R.id.card_movie );
        }
    }


    @Nullable
    @Override
    protected MoviesModel getItem(int position) {
        return super.getItem( position );
    }

    public interface OnMovieClickListener
    {
        void doOnClick(MoviesModel moviesModel);
    }
}
