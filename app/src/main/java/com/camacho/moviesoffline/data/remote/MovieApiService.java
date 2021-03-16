package com.camacho.moviesoffline.data.remote;

import com.camacho.moviesoffline.data.remote.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {
    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
}
