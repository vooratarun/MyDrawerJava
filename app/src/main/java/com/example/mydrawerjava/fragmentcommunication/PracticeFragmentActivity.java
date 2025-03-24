package com.example.mydrawerjava.fragmentcommunication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mydrawerjava.R;





public class PracticeFragmentActivity extends  AppCompatActivity implements FragmentCommunication{

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_fragment);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentAContainer, fragmentA);
        fragmentTransaction.add(R.id.fragmentBContainer, fragmentB);
        fragmentTransaction.commit();

    }

    @Override
    public void sendToFragmentA(String message) {
        fragmentA.updateText(message);
    }

    @Override
    public void sendToFragmentB(String message) {
        fragmentB.updateText(message);

    }
}