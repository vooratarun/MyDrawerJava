package com.example.mydrawerjava.retro;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.MenuItem;
import android.widget.Toast;

import com.example.mydrawerjava.databinding.ActivityRetrofitBinding;
import com.example.mydrawerjava.mvvmretrofit.ApiService;
import com.example.mydrawerjava.mvvmretrofit.Post;


public class RetrofitActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    private ActivityRetrofitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_retrofit);

        binding = ActivityRetrofitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        Toolbar toolbar = findViewById(R.id.toolbar);
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("Retrofit List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        recyclerView = findViewById(R.id.recyclerView);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchPosts();
    }

    private  void fetchPosts() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Post>> call = apiService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null) {

                    postAdapter = new PostAdapter(response.body());
                    recyclerView.setAdapter(postAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

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
}