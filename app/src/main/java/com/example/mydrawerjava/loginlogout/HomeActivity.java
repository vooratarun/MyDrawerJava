package com.example.mydrawerjava.loginlogout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mydrawerjava.R;

public class HomeActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        Button buttonLogout = findViewById(R.id.buttonLogout);

        // Display username
        textViewWelcome.setText("Welcome, " + sessionManager.getUsername());

        buttonLogout.setOnClickListener(v -> {
            sessionManager.logout();
            startActivity(new Intent(HomeActivity.this, Login2Activity.class));
            finish();
        });

    }
}