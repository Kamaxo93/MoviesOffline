package com.camacho.moviesoffline.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.camacho.moviesoffline.app.MyApp;
import com.camacho.moviesoffline.data.local.MovieRoomDataBase;
import com.camacho.moviesoffline.data.local.dao.MovieDao;
import com.camacho.moviesoffline.data.local.entity.MovieEntity;
import com.camacho.moviesoffline.data.network.NetworkBoundResource;
import com.camacho.moviesoffline.data.network.Resource;
import com.camacho.moviesoffline.data.remote.ApiConstant;
import com.camacho.moviesoffline.data.remote.MovieApiService;
import com.camacho.moviesoffline.data.remote.RequestInterceptor;
import com.camacho.moviesoffline.data.remote.model.MoviesResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private final MovieApiService service;
    private final MovieDao movieDao;

    public MovieRepository () {
        //Local > Room
        MovieRoomDataBase movieRoomDataBase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDataBase.class,
                "db_movies"
        ).build();

        movieDao = movieRoomDataBase.getMovieDao();



        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new RequestInterceptor());
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(MovieApiService.class);

    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return service.loadPopularMovies();
            }
        }.getAsLiveData();
    }
}
