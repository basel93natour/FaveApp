package com.basel.natour.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.basel.natour.myapplication.R;
import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesResponse;

public class MoviesListPagedAdapter extends PagedListAdapter<MoviesModel,MoviesListPagedAdapter.ViewHolder> {

    Context context;
    public MoviesListPagedAdapter(Context context) {
        super( new MovieDiffUtil() );
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.movie_list_item,parent,false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder , int position) {
        MoviesModel moviesModel=getItem( position );
        holder.textView.setText( moviesModel.getTitle() );

    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            textView=itemView.findViewById( R.id.movie_name );
        }
    }


    @Nullable
    @Override
    protected MoviesModel getItem(int position) {
        return super.getItem( position );
    }
}
