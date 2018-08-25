package com.arctouch.codechallenge.model;

import java.util.List;

public class GenreResponse {

    public List<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreResponse)) return false;

        GenreResponse that = (GenreResponse) o;

        return genres != null ? genres.equals(that.genres) : that.genres == null;
    }

    @Override
    public int hashCode() {
        return genres != null ? genres.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GenreResponse{" +
                "genres=" + genres +
                '}';
    }
}
