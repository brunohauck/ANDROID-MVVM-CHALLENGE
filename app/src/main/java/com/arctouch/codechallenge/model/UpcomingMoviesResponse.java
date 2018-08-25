package com.arctouch.codechallenge.model;

import com.squareup.moshi.Json;

import java.util.List;

public class UpcomingMoviesResponse {

    public int page;
    public List<Movie> results;
    @Json(name = "total_pages")
    public int totalPages;
    @Json(name = "total_results")
    public int totalResults;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpcomingMoviesResponse)) return false;

        UpcomingMoviesResponse that = (UpcomingMoviesResponse) o;

        if (page != that.page) return false;
        if (totalPages != that.totalPages) return false;
        if (totalResults != that.totalResults) return false;
        return results != null ? results.equals(that.results) : that.results == null;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + (results != null ? results.hashCode() : 0);
        result = 31 * result + totalPages;
        result = 31 * result + totalResults;
        return result;
    }

    @Override
    public String toString() {
        return "UpcomingMoviesResponse{" +
                "page=" + page +
                ", results=" + results +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
