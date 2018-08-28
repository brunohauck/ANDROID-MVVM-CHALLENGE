package com.arctouch.codechallenge.home.fragments;

import com.arctouch.codechallenge.home.detail.DetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListMoviesFragment provideListMoviesFragment();


    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}
