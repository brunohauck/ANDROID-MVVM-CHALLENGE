package com.arctouch.codechallenge.module;

import com.arctouch.codechallenge.home.HomeMainActivity;
import com.arctouch.codechallenge.home.fragments.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract HomeMainActivity bindMainActivity();
}
