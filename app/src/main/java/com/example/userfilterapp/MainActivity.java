package com.example.userfilterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.userfilterapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    final String TAG = "Demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

    }
}