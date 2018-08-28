package com.arctouch.codechallenge.home.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.base.BaseFragment;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.arctouch.codechallenge.util.ViewModelFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import butterknife.BindView;


public class DetailsFragment extends BaseFragment {

    @BindView(R.id.tv_repo_name) TextView repoNameTextView;
    @BindView(R.id.tv_repo_description) TextView repoDescriptionTextView;
    @BindView(R.id.releaseDate) TextView textViewReleaseDate;
    @BindView(R.id.genresTextView) TextView genresTextView;
    @BindView(R.id.movie_poster) ImageView moviePoster;
    @BindView(R.id.movie_back_drop) ImageView movieBackDrop;

    @Inject
    ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        detailsViewModel.restoreFromBundle(savedInstanceState);
        displayRepo();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailsViewModel.saveToBundle(outState);
    }

    private void displayRepo() {
        detailsViewModel.getSelectedMovie().observe(this, movie -> {
            if (movie != null) {
                MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();
                repoNameTextView.setText(movie.title);
                repoDescriptionTextView.setText(movie.overview);
                textViewReleaseDate.setText(movie.release_date);
                String posterPath = movie.poster_path;
                genresTextView.setText(TextUtils.join(", ", movie.genres));
                if (TextUtils.isEmpty(posterPath) == false) {
                    Glide
                            .with(this)
                            .load(movieImageUrlBuilder.buildPosterUrl(movie.poster_path))
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                                    return false;   // return false if you want Glide to handle everything else.
                                }
                            })
                            .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                            .centerCrop()
                            .crossFade()
                            .into(moviePoster);

                }
                String backdropPath = movie.backdrop_path;
                if (TextUtils.isEmpty(posterPath) == false) {
                    Glide
                            .with(this)
                            .load(movieImageUrlBuilder.buildPosterUrl(movie.backdrop_path))
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
                            .into(movieBackDrop);

                }
            }
        });
    }
}
