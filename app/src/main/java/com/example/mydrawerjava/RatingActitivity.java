package com.example.mydrawerjava;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RatingActitivity extends AppCompatActivity {

    private static final String PREFS_NAME = "rating_prefs";
    private static final String LAUNCH_COUNT_KEY = "launch_count";
    private static final int LAUNCH_THRESHOLD = 5; // Show after 5 launches

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rating_actitivity);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Track app launches
        int launchCount = prefs.getInt(LAUNCH_COUNT_KEY, 0) + 1;
        prefs.edit().putInt(LAUNCH_COUNT_KEY, launchCount).apply();

        Button rateButton = findViewById(R.id.btn_rate);
        rateButton.setOnClickListener(v -> showCustomRatingDialog());

        // Automatically show rating prompt after X launches
        if (launchCount >= LAUNCH_THRESHOLD) {
            showCustomRatingDialog();
        }

    }


    private void showCustomRatingDialog() {
        prefs.edit().putInt(LAUNCH_COUNT_KEY, 0).apply();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rate Our App")
                .setMessage("If you enjoy using our app, please take a moment to rate it. Thanks for your support!")
                .setPositiveButton("Rate Now", (dialog, which) -> openPlayStore())
                .setNegativeButton("Later", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void openPlayStore() {
        String appPackageName = getPackageName(); // Get package name dynamically
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}