package com.example.mydrawerjava.loginlogout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mydrawerjava.R;

public class Login2Activity extends AppCompatActivity {

    private EditText editTextUsername;
    private Button buttonLogin;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);

        sessionManager = new SessionManager(this);

        // If user is already logged in, go to HomeActivity
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        editTextUsername = findViewById(R.id.editTextUsername);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            if (!username.isEmpty()) {
                sessionManager.login(username);
                Toast.makeText(Login2Activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login2Activity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(Login2Activity.this, "Enter Username", Toast.LENGTH_SHORT).show();
            }
        });

    }
}