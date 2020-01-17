package com.basel.natour.myapplication.database;

import com.basel.natour.myapplication.model.MoviesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TypeConverter {


    @androidx.room.TypeConverter
    public static List<MoviesModel> fromMovie(String value) {
        Type listType = new TypeToken<List<MoviesModel>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @androidx.room.TypeConverter
    public static String fromMoviesList(List<MoviesModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @androidx.room.TypeConverter
    public static List<Integer> fromInteger(String value) {
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @androidx.room.TypeConverter
    public static String fromIntegerList(List<Integer> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
