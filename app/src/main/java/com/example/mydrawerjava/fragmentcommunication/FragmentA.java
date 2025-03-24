package com.example.mydrawerjava.fragmentcommunication;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mydrawerjava.R;

public class FragmentA extends Fragment {

    private FragmentCommunication communication;
    private EditText editTextA;
    private TextView textViewA;

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
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        editTextA = view.findViewById(R.id.editTextA);
        textViewA = view.findViewById(R.id.textViewA);
        Button buttonSendToB = view.findViewById(R.id.buttonSendToB);

//        using communication interface
//        buttonSendToB.setOnClickListener(v -> {
//            String message = editTextA.getText().toString();
//            communication.sendToFragmentB(message);
//        });

        // using viewModel
        buttonSendToB.setOnClickListener(v -> {
            String message = editTextA.getText().toString();
            viewModel.sendMessageToB(message);
        });

        viewModel.getMessageFromB().observe(getViewLifecycleOwner(), message -> {
            textViewA.setText("Received from B: " + message);
        });




        return view;
    }

    // when using interface, calls directly from activity
    public void updateText(String message) {
        textViewA.setText("Received from B: " + message);
    }
}
