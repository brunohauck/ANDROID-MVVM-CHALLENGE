package com.arctouch.codechallenge.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.base.BaseActivity;
import com.arctouch.codechallenge.home.fragments.ListMoviesFragment;

public class HomeMainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_home_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListMoviesFragment()).commit();
    }

}
