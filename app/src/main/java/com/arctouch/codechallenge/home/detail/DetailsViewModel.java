package com.arctouch.codechallenge.home.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import com.arctouch.codechallenge.api.ServiceMovies;
import com.arctouch.codechallenge.model.Movie;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class DetailsViewModel extends ViewModel {

    private final ServiceMovies serviceMovies;
    private CompositeDisposable disposable;

    private final MutableLiveData<Movie> selectedMovie = new MutableLiveData<>();

    public LiveData<Movie> getSelectedMovie() {
        return selectedMovie;
    }

    @Inject
    public DetailsViewModel(ServiceMovies serviceMovies) {
        this.serviceMovies = serviceMovies;
        disposable = new CompositeDisposable();
    }

    public void setSelectedRepo(Movie movie) {
        selectedMovie.setValue(movie);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedMovie.getValue() != null) {
            outState.putStringArray("repo_details", new String[] {
                    selectedMovie.getValue().overview,
                    selectedMovie.getValue().title
            });
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedMovie.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                //loadRepo(savedInstanceState.getStringArray("repo_details"));
                Long myLong= new Long(selectedMovie.hashCode());
                loadRepo(myLong);
            }
        }
    }

    private void loadRepo(Long repo_details) {
        disposable.add(serviceMovies.getRepo(repo_details).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Movie>() {
            @Override
            public void onSuccess(Movie value) {
                selectedMovie.setValue(value);
            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
