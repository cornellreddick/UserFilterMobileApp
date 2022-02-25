package com.example.userfilterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.userfilterapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements UserFragment.UserFragmentListener {
    ActivityMainBinding binding;
    final String TAG = "Demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

       getSupportFragmentManager().beginTransaction()
               .add(R.id.containerView, new UserFragment())
               .commit();

    }

    @Override
    public void gotoFilterByState() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new StateFragment())
                .addToBackStack(null)
                .commit();

    }
}