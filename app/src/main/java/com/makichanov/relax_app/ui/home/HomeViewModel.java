package com.makichanov.relax_app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.makichanov.relax_app.RelaxAppContext;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final RelaxAppContext context;

    public HomeViewModel() {
        context = RelaxAppContext.getInstance();
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        mText.setValue("Welcome, " + context.getCurrentUser().getUsername());
        return mText;
    }
}