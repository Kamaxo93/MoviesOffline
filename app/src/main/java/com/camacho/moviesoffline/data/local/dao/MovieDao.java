package com.camacho.moviesoffline.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.camacho.moviesoffline.data.local.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * From movies")
    LiveData<List<MovieEntity>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntityList);
}
