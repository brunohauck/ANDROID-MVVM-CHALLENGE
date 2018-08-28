package com.arctouch.codechallenge.model;

import com.squareup.moshi.Json;

import java.util.List;

public class UpcomingMoviesResponse {

    public int page;
    public List<Movie> results;
    public int total_pages;
    public int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
/*
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
    } */
}
