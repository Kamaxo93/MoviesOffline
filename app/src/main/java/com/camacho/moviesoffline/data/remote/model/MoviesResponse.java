package com.camacho.moviesoffline.data.remote.model;

import com.camacho.moviesoffline.data.local.entity.MovieEntity;

import java.util.List;

public class MoviesResponse {
    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
