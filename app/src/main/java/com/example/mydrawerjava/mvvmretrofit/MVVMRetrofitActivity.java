package com.example.mydrawerjava.mvvmretrofit;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydrawerjava.R;

import java.util.ArrayList;
import java.util.List;

public class MVVMRetrofitActivity extends AppCompatActivity {


    private PostViewModel postViewModel;
    private PostAdapter2 postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mvvmretrofit);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MVVM Retrofit List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter2(new ArrayList<>(), new PostAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(String itemText) {
                Toast.makeText(MVVMRetrofitActivity.this, itemText, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(postAdapter);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                    updateUI(posts);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle back button press
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI(List<Post> posts) {
        postAdapter.setPostList(posts);
    }
}