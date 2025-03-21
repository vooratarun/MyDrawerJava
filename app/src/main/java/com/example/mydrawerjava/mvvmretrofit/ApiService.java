package com.example.mydrawerjava.mvvmretrofit;

import com.example.mydrawerjava.data.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("posts")
    Call<List<Post>> getPosts();


    @GET("users")  // Example endpoint
    Call<List<User>> getUsers();
}
