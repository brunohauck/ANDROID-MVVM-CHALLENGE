package com.arctouch.codechallenge.home.fragments;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.RepoViewHolder>{

    private MovieSelectedListener movieSelectedListener;
    private final List<Movie> data = new ArrayList<>();

    static Context ctx;
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
        ctx = parent.getContext();
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

    public void add(Movie r) {
        data.add(r);
        notifyItemInserted(data.size() - 1);
    }

    public void addAll(List<Movie> moveResults) {
        for (Movie result : moveResults) {
            add(result);
        }
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_year) TextView movieYearTextView;
        @BindView(R.id.movie_title) TextView movieTitleTextView;
        @BindView(R.id.movie_desc) TextView movieDescTextView;
        @BindView(R.id.movie_poster) ImageView moviePoster;


        private Movie movie;
        private MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();
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
            movieYearTextView.setText(movie.title);
            movieTitleTextView.setText(movie.release_date);
            movieDescTextView.setText(movie.overview);
            String posterPath = movie.poster_path;

            if (TextUtils.isEmpty(posterPath) == false) {
                Glide
                        .with(ctx)
                        .load(movieImageUrlBuilder.buildPosterUrl(movie.poster_path))
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                                //holder.mProgress.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                // image ready, hide progress now
                                //holder.mProgress.setVisibility(View.GONE);
                                return false;   // return false if you want Glide to handle everything else.
                            }
                        })
                        .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                        .centerCrop()
                        .crossFade()
                        .into(moviePoster);

            }
            /*
            Glide
                    .with(itemView)
                    .load(MovieImageUrlBuilder.buildPosterUrl(movie.posterPath))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                            movieVH.mProgress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            // image ready, hide progress now
                            movieVH.mProgress.setVisibility(View.GONE);
                            return false;   // return false if you want Glide to handle everything else.
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                    .centerCrop()
                    .crossFade()
                    .into(movieVH.mPosterImg);

                    */

        }
        /*
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
        */
    }
}
