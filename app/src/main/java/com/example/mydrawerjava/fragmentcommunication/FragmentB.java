package com.example.mydrawerjava.fragmentcommunication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydrawerjava.R;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;


public class FragmentB extends Fragment {

    private FragmentCommunication communication;
    private EditText editTextB;
    private TextView textViewB;
    private SharedViewModel viewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            communication = (FragmentCommunication) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentCommunication");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        editTextB = view.findViewById(R.id.editTextB);
        textViewB = view.findViewById(R.id.textViewB);
        Button buttonSendToA = view.findViewById(R.id.buttonSendToA);

//        buttonSendToA.setOnClickListener(v -> {
//            String message = editTextB.getText().toString();
//            communication.sendToFragmentA(message);
//        });


        buttonSendToA.setOnClickListener(v -> {
            String message = editTextB.getText().toString();
            viewModel.sendMessageToA(message);
        });

        viewModel.getMessageFromA().observe(getViewLifecycleOwner(), message -> {
            textViewB.setText("Received from A: " + message);
        });


        return view;
    }

    public void updateText(String message) {
        textViewB.setText("Received from A: " + message);
    }
}
