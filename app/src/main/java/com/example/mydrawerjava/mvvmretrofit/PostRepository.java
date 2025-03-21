package com.example.mydrawerjava.mvvmretrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mydrawerjava.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private final ApiService apiService;

    public PostRepository() {
        apiService = RetrofitClient.getInstance();
    }

    public LiveData<List<Post>> getPosts() {
        MutableLiveData<List<Post>> postLiveData = new MutableLiveData<>();
        apiService.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    postLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postLiveData.setValue(null);
            }
        });
        return postLiveData;
    }
}
