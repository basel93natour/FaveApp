package com.basel.natour.myapplication.network;

import com.basel.natour.myapplication.model.MovieDetailsModel;
import com.basel.natour.myapplication.model.MoviesResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesServiceApi {


    @GET("discover/movie")
    Single<MoviesResponse> getMovies(
            @Query( "api_key") String api_key
            ,@Query("primary_release_date.lte") String releaseDate
            , @Query( "sort_by" ) String sortBy
            , @Query( "page" ) int page);


    @GET("movie/{id}")
    Single<MovieDetailsModel> getMovieDetails(@Path( "id" ) int id, @Query( "api_key") String api_key);
}
