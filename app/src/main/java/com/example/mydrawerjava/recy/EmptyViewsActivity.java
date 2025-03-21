package com.example.mydrawerjava.recy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydrawerjava.R;

import java.util.ArrayList;
import java.util.List;


public class EmptyViewsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_empty_views);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample Data
        itemList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            itemList.add("Item " + i);
        }

        adapter = new MyAdapter(itemList, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String itemText) {

                Toast.makeText(EmptyViewsActivity.this, itemText, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EmptyViewsActivity.this, DetailsActivity.class);
                intent.putExtra("ITEM_TEXT", itemText);
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);
    }
}