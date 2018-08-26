package com.arctouch.codechallenge.home.fragments;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListMoviesFragment provideListMoviesFragment();

    /*
    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment(); */
}
