package com.example.mydrawerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mydrawerjava.ui.main.ViewModelFragment;

public class ViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ViewModelFragment.newInstance())
                    .commitNow();
        }
    }
}