package com.arctouch.codechallenge.home.fragments;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.RepoViewHolder>{

    private MovieSelectedListener movieSelectedListener;
    private final List<Movie> data = new ArrayList<>();

    MovieListAdapter(ListMoviesViewModel viewModel, LifecycleOwner lifecycleOwner, MovieSelectedListener movieSelectedListener) {
        this.movieSelectedListener = movieSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new RepoViewHolder(view, movieSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name) TextView repoNameTextView;
        @BindView(R.id.tv_repo_description) TextView repoDescriptionTextView;
        @BindView(R.id.tv_forks) TextView forksTextView;
        @BindView(R.id.tv_stars) TextView starsTextView;

        private Movie movie;

        RepoViewHolder(View itemView, MovieSelectedListener movieSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(movie != null) {
                    movieSelectedListener.onMovieSelected(movie);
                }
            });
        }

        void bind(Movie movie) {
            this.movie = movie;
            repoNameTextView.setText(movie.title);
            repoDescriptionTextView.setText(movie.releaseDate);
            forksTextView.setText(String.valueOf("10"));
            starsTextView.setText(String.valueOf("10"));
        }
    }
}
