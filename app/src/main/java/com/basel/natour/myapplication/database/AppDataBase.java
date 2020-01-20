package com.basel.natour.myapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.basel.natour.myapplication.model.MoviesModel;
import com.basel.natour.myapplication.model.MoviesResponse;

@Database( entities ={MoviesModel.class} ,version = 1,exportSchema = false)
@TypeConverters({TypeConverter.class})
public  abstract class AppDataBase extends RoomDatabase {

    public abstract MoviesDao moviesDao();
}
