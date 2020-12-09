package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> liveData;

    public SharedViewModel() {
        liveData = new MutableLiveData<>();
    }

    public LiveData getLiveData(){
        return liveData;
    }

    public void setLiveData(String s){
        liveData.setValue(s);
    }



}
