package com.basel.natour.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.basel.natour.myapplication.model.MoviesModel;

public class MovieDiffUtil  extends DiffUtil.ItemCallback<MoviesModel>
{
    @Override
    public boolean areItemsTheSame(@NonNull MoviesModel oldItem , @NonNull MoviesModel newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull MoviesModel oldItem , @NonNull MoviesModel newItem) {
        return false;
    }
}
