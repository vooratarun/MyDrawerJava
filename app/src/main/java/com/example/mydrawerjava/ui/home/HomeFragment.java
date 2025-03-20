package com.example.mydrawerjava.ui.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydrawerjava.CoordinateLayoutActivity;
import com.example.mydrawerjava.MVVMRetrofitActivity;
import com.example.mydrawerjava.MainActivity;
import com.example.mydrawerjava.PracticeViewpagerActivity;
import com.example.mydrawerjava.ProgressbarActivity;
import com.example.mydrawerjava.R;
import com.example.mydrawerjava.RoomDatabaseActivity;
import com.example.mydrawerjava.ScrollingActivity;
import com.example.mydrawerjava.SharedPrefActivity;
import com.example.mydrawerjava.ViewModelActivity;
import com.example.mydrawerjava.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CoordinateLayoutActivity.class);
                startActivity(intent);
            }
        });

        binding.practiceviewpageractivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PracticeViewpagerActivity.class);
                startActivity(intent);
            }
        });

        binding.scrollingactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScrollingActivity.class);
                startActivity(intent);
            }
        });

        binding.viewmodelactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewModelActivity.class);
                startActivity(intent);
            }
        });

        binding.alertdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  normalAlertDialog();
              //  customButtonIcon();
                customEditTextLayout();
            }
        });

        binding.progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgressbarActivity.class);
                startActivity(intent);
            }
        });

        binding.sharedpref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SharedPrefActivity.class);
                startActivity(intent);
            }
        });

        binding.roomdatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RoomDatabaseActivity.class);
                startActivity(intent);

            }
        });



        return root;
    }

    private  void normalAlertDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Alert")
                .setMessage("This is a simple alert dialog.")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Action on OK click
                    Toast.makeText(getContext(), "OK Clicked", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void customButtonIcon() {
        new AlertDialog.Builder(getContext())
                .setTitle("Warning")
                .setMessage("This action cannot be undone!")
                .setIcon(R.drawable.ic_camera_black_24dp) // Set an icon
                .setPositiveButton("Proceed", (dialog, which) -> {
                    Toast.makeText(getContext(), "Proceeding...", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void customEditTextLayout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter Name");

        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String userInput = input.getText().toString();
            Toast.makeText(getContext(), "Entered: " + userInput, Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}