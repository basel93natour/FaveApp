package com.basel.natour.myapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;

import com.basel.natour.myapplication.model.MoviesModel;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert
    void insertMovies(List<MoviesModel> moviesModels);
}
