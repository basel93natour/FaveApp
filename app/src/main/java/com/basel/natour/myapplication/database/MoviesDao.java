package com.basel.natour.myapplication.database;

import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.basel.natour.myapplication.model.MoviesModel;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MoviesModel> moviesModels);

    @Query("select * from movie_table")
    List<MoviesModel> getMoviesList();

    @Query( "Delete from movie_table" )
    int deleteMovies();
}
