package com.example.mydrawerjava;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class PostViewModel extends ViewModel {
    private final PostRepository repository;

    public PostViewModel() {
        repository = new PostRepository();
    }

    public LiveData<List<Post>> getPosts() {
        return repository.getPosts();
    }
}

