package com.arctouch.codechallenge.data;

import com.arctouch.codechallenge.model.Genre;
import java.util.ArrayList;
import java.util.List;

public class Cache {

    private static List<Genre> genres = new ArrayList<>();

    public static List<Genre> getGenres() {
        return genres;
    }

    public static void setGenres(List<Genre> genres) {
        Cache.genres.clear();
        Cache.genres.addAll(genres);
    }
}
