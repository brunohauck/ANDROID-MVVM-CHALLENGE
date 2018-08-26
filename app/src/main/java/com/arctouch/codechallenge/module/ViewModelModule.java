package com.arctouch.codechallenge.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.arctouch.codechallenge.home.fragments.ListMoviesViewModel;
import com.arctouch.codechallenge.util.ViewModelFactory;
import com.arctouch.codechallenge.util.ViewModelKey;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListMoviesViewModel.class)
    abstract ViewModel bindListViewModel(ListMoviesViewModel listViewModel);

    /*
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);
    */
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
