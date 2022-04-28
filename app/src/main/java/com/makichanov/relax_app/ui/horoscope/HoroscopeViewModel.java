package com.makichanov.relax_app.ui.horoscope;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HoroscopeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HoroscopeViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}