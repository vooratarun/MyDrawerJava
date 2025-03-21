package com.example.mydrawerjava.mvvmretrofit;

import androidx.annotation.NonNull;

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
