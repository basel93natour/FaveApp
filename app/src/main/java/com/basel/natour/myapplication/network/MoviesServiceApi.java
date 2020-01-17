package com.basel.natour.myapplication.network;

import com.basel.natour.myapplication.model.MoviesResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesServiceApi {


    @GET("movie") //&primary_release_date.lte=2016-12-31&sort_by=release_date.de sc&page=1
    Single<List<MoviesResponse>> getMovies(@Query("primary_release_date.lte") String releaseDate
            , @Query( "sort_by" ) String sortBy
            , @Query( "page" ) int page);
}
