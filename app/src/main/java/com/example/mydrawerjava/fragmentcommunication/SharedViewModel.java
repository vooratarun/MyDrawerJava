package com.example.mydrawerjava.fragmentcommunication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> messageFromA = new MutableLiveData<>();
    private final MutableLiveData<String> messageFromB = new MutableLiveData<>();

    public void sendMessageToB(String message) {
        messageFromA.setValue(message);
    }

    public LiveData<String> getMessageFromA() {
        return messageFromA;
    }

    public void sendMessageToA(String message) {
        messageFromB.setValue(message);
    }

    public LiveData<String> getMessageFromB() {
        return messageFromB;
    }
}
