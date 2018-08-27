package com.arctouch.codechallenge.api;

import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by brunodelhferreira on 26/08/2018.
 */

public class ServiceMovies {

    private final TmdbApi tmdbApi;

    @Inject
    public ServiceMovies(TmdbApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }



    public Single<UpcomingMoviesResponse> getUpcomingMovies() {
        return tmdbApi.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1L, TmdbApi.DEFAULT_REGION);
    }
    public Single<UpcomingMoviesResponse> getUpcomingMoviesNext(Long page) {
        return tmdbApi.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page, TmdbApi.DEFAULT_REGION);
    }
    /*
    public Single<Repo> getRepo(String owner, String name) {
        return repoService.getRepo(owner, name);
    } */
}
