package com.example.mydrawerjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class SharedPrefActivity extends AppCompatActivity {


    private EditText etUsername;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_pref);

        etUsername = findViewById(R.id.etUsername);
        tvResult = findViewById(R.id.tvResult);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnLoad);

        SharedPreferencesHelper preferencesHelper = new SharedPreferencesHelper(this);

        // store string and retrieve string
//        btnSave.setOnClickListener(v -> {
//            String username = etUsername.getText().toString();
//            preferencesHelper.saveName(username);
//
//        });
//
//        // Load Data on Button Click
//        btnLoad.setOnClickListener(v -> {
//            String name = preferencesHelper.getName();
//            tvResult.setText(name);
//        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Post> postList = new ArrayList<>();
                postList.add(new Post(1,1,"title1","body1"));
                postList.add(new Post(2,2,"title2","body2"));
                postList.add(new Post(3,3,"title3","body3"));

                preferencesHelper.savePostList(postList);

            }
        });

        btnLoad.setOnClickListener(v -> {
            List<Post> savedPosts = preferencesHelper.getPostList();
            if (!savedPosts.isEmpty()) {
                StringBuilder result = new StringBuilder();
                for (Post post : savedPosts) {
                    result.append(post.getTitle()).append("\n\n");
                }
                tvResult.setText(result.toString());
            } else {
                tvResult.setText("No Posts Found");
            }
        });

    }
}