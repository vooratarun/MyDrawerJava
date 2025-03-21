package com.example.mydrawerjava;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.mydrawerjava.data.AppDatabase;
import com.example.mydrawerjava.data.User;
import com.example.mydrawerjava.data.UserDao;
import com.example.mydrawerjava.databinding.ActivityRoomDatabaseBinding;
import com.example.mydrawerjava.mvvmretrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDatabaseActivity extends AppCompatActivity {


    private ActivityRoomDatabaseBinding binding;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //setContentView(R.layout.activity_room_database);

        binding = ActivityRoomDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        AppDatabase db = AppDatabase.getInstance(this);
        userDao = db.userDao();

        binding.buttonInsertUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });

        binding.buttonGetUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllUsers();
            }
        });

        binding.fetchUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAndSaveUsers();
            }
        });

        binding.deleteUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllUsers();
            }
        });

    }

    private void deleteAllUsers() {
        userDao.deleteAllUsers();
        getAllUsers();
    }

    private void insertUser() {
        String name = binding.editTextName.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter both name and email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user into Room database
        User newUser = new User(name, email);
        userDao.insertUser(newUser);

        Toast.makeText(this, "User Inserted!", Toast.LENGTH_SHORT).show();

        // Clear input fields
        binding.editTextName.setText("");
        binding.editTextEmail.setText("");
    }

    private void getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        if (userList.isEmpty()) {
            binding.textViewUsers.setText("No users found.");
            return;
        }

        // Build a string with all user names
        StringBuilder userNames = new StringBuilder();
        for (User user : userList) {
            userNames.append(user.getName()).append("\n");
        }

        // Display names in TextView
        binding.textViewUsers.setText(userNames.toString());
    }

    private void fetchAndSaveUsers() {
        ApiService apiService = RetrofitClient.getInstance();
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    for(User user : users){
                        Log.i("users",  user.getId() + "->" + user.getName() + "" );
                    }
                    userDao.insertUsers(users);
                    Toast.makeText(RoomDatabaseActivity.this, "Users Saved!", Toast.LENGTH_SHORT).show();
                    getAllUsers();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(RoomDatabaseActivity.this, "Failed to Fetch Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void displayUsers() {
//        List<User> userList = userDao.getAllUsers();
//        StringBuilder userNames = new StringBuilder();
//
//        for (User user : userList) {
//            userNames.append(user.getName()).append("\n");
//        }
//
//        binding.fetchedUsersText.setText(userNames.toString());
//    }


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