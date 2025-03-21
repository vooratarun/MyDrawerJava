package com.example.mydrawerjava.mvvmroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydrawerjava.R;
import com.example.mydrawerjava.databinding.ActivityMvvmroomBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


import java.util.Random;


public class MVVMRoomActivity extends AppCompatActivity {


    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mvvmroom);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes, new NoteAdapter.OnDeleteClickListener() {
                    @Override
                    public void onDeleteClick(Note note) {
                        noteViewModel.delete(note);
                    }

                    @Override
                    public void onViewClick(Note note) {
//                        Toast.makeText(MVVMRoomActivity.this, note.getTitle(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MVVMRoomActivity.this, NoteDetailsActivity.class);
                        intent.putExtra(NoteDetailsActivity.EXTRA_TITLE, note.getTitle());
                        intent.putExtra(NoteDetailsActivity.EXTRA_DESCRIPTION, note.getDescription());
                        intent.putExtra(NoteDetailsActivity.EXTRA_PRIORITY, note.getId());
                        startActivity(intent);

                    }
                });
            }
        });

        FloatingActionButton fab = findViewById(R.id.button_add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                noteViewModel.insert(new Note("new note  " + random.nextInt(100),"note description"));
            }
        });


    }
}