package com.example.mydrawerjava;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mydrawerjava.mvvmretrofit.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_NAME = "username";
    private static final String KEY_LIST = "string_list";

    private static final String KEY_POST = "post_data";

    private static final String KEY_POST_LIST = "post_list";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Gson gson;


    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    public void saveName(String name) {
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString(KEY_NAME, "No name found");
    }

    // Save List of Strings
    public void saveStringList(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);  // Convert List to JSON
        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    // Retrieve List of Strings
    public List<String> getStringList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }

    // Save Post Model
    public void savePost(Post post) {
        String json = gson.toJson(post);
        editor.putString(KEY_POST, json);
        editor.apply();
    }

    // Retrieve Post Model
    public Post getPost() {
        String json = sharedPreferences.getString(KEY_POST, null);
        return json != null ? gson.fromJson(json, Post.class) : null;
    }

    public void clearData() {
        editor.clear();
        editor.apply();
    }

    // Save List of Posts
    public void savePostList(List<Post> postList) {
        String json = gson.toJson(postList);
        editor.putString(KEY_POST_LIST, json);
        editor.apply();
    }

    // Retrieve List of Posts
    public List<Post> getPostList() {
        String json = sharedPreferences.getString(KEY_POST_LIST, null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<Post>>() {}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }

    // Clear Post List
    public void clearPostList() {
        editor.remove(KEY_POST_LIST);
        editor.apply();
    }
}

