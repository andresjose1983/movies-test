package com.example.andres.movies_test.service;

import com.example.andres.movies_test.model.GenreResponse;
import com.example.andres.movies_test.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by andres on 27/01/17.
 */

public interface IClient {

    @GET("/genre/movie/list")
    Call<GenreResponse> getGenre(@Query("api_key") String apiKey);

    @GET("/genre/{genre_id}/movies?include_adult=false&sort_by=created_at.as")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey, @Path("genre_id") String genreId);
}
