package com.example.mydrawerjava.mvvmroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mydrawerjava.R;
import androidx.appcompat.widget.Toolbar;

public class NoteDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.notes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.notes.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.notes.EXTRA_PRIORITY";

    private TextView textViewTitle, textViewDescription, textViewPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note_details);


        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back button
        }


        textViewTitle = findViewById(R.id.text_view_title);
        textViewDescription = findViewById(R.id.text_view_description);
        textViewPriority = findViewById(R.id.text_view_priority);

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            textViewTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            textViewDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            int priority = intent.getIntExtra(EXTRA_PRIORITY, 1);
            textViewPriority.setText("Priority: " + priority);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Close the activity when back button is pressed
        return true;
    }
}